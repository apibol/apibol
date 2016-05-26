package event.domain;

import event.domain.exception.GameIsNotInEventRangeDate;
import event.domain.specification.IsInEventPeriod;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.time.temporal.ChronoUnit.HOURS;

/**
 * @author Claudio E. de Oliveira on 27/02/16.
 */
@Data
@Document(collection = "event")
public class Event {

    /**
     * Default time to block
     */
    private static final Integer DEFAULT_HOURS = 2;

    @Id
    private String id;

    private String name;

    private Period period;

    private Boolean open = Boolean.FALSE;

    private Set<Game> games = new HashSet<>();

    private Set<User> participants = new HashSet<>();

    /**
     * Hours before game to accept new predictions
     */
    private Integer hoursLimitToBlock = DEFAULT_HOURS;

    private User owner;

    /**
     * Default constructor for frameworks
     */
    Event() {
    }

    /**
     * Private constructor for factory
     *
     * @param id
     * @param name
     * @param period
     * @param open
     * @param owner
     */
    private Event(String id, String name, Period period, Boolean open, User owner,Integer hoursLimitToBlock) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.open = open;
        this.owner = owner;
        this.hoursLimitToBlock = hoursLimitToBlock;
    }

    /**
     * Factory method
     *
     * @param id
     * @param name
     * @param period
     * @param open
     * @param owner
     * @param hoursLimitToBlock
     * @return
     */
    public static Event newEvent(String id, String name, Period period, Boolean open, User owner, Integer hoursLimitToBlock) {
        return new Event(id, name, period, open, owner,hoursLimitToBlock);
    }

    /**
     * Add game in event
     *
     * @param game
     * @return
     */
    public Event addGame(Game game) throws GameIsNotInEventRangeDate{
        if (new IsInEventPeriod(this).isSatisfiedBy(game)){
            this.games.add(game);
        }else{
            throw new GameIsNotInEventRangeDate(game);
        }
        return this;
    }

    /**
     * Remove game from event
     *
     * @param gameId
     * @return
     */
    public Event removeGame(String gameId) {
        this.games.removeIf(element -> element.id.equals(gameId));
        return this;
    }

    /**
     * Select game by Id
     * @param gameId
     * @return
     */
    public Game gameById(String gameId){
        return this.games.stream().filter(game -> game.getId().equals(gameId)).findFirst().get();
    }

    /**
     * Add participant in event
     * @param user
     * @return
     */
    public Event addParticipant(User user){
        this.participants.add(user);
        return this;
    }

    public Boolean isOpenForPredictions(String gameId){
        final Game game = this.gameById(gameId);
        if(Objects.nonNull(game)){
            final LocalDateTime requestTime = LocalDateTime.now();
            final LocalDateTime gameTime = game.getTime();
            final long hours = HOURS.between(gameTime, requestTime);
            return (int) hours > this.hoursLimitToBlock;
        }
        return Boolean.FALSE;
    }

}
