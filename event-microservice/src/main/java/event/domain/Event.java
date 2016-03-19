package event.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Claudio E. de Oliveira on 27/02/16.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Document(collection = "event")
public class Event {

    @Id
    private String id;

    private String name;

    private Period period;

    private Boolean open = Boolean.FALSE;

    private Set<Game> games = new HashSet<>();

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
    private Event(String id, String name, Period period, Boolean open, User owner) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.open = open;
        this.owner = owner;
    }

    /**
     * Factory method
     *
     * @param id
     * @param name
     * @param period
     * @param open
     * @param owner
     * @return
     */
    public static Event newEvent(String id, String name, Period period, Boolean open, User owner) {
        return new Event(id, name, period, open, owner);
    }

    /**
     * Add game in event
     *
     * @param game
     * @return
     */
    public Event addGame(Game game) {
        this.games.add(game);
        return this;
    }

    /**
     * Remove game from event
     *
     * @param gameId
     * @return
     */
    public Event removeGame(String gameId) {
        this.games = this.games.stream().filter(element -> {
            return !element.id.equals(gameId);
        }).collect(Collectors.toSet());
        return this;
    }

}
