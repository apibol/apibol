package event.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Claudio E. de Oliveira on 27/02/16.
 */
@Data @EqualsAndHashCode(callSuper = false)
@Document(collection = "event")
public class Event {
    
    @Id
    private String id;
    
    private String name;
    
    private Period period;
    
    private Boolean open = Boolean.FALSE;
    
    private List<Game> games = new ArrayList<>();
    
    private User owner;

    private Event(String id, String name, Period period, Boolean open, User owner) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.open = open;
        this.owner = owner;
    }

    public static Event newChampionship(String id, String name, Period period, Boolean open, User owner) {
        return new Event(id, name, period, open, owner);
    }

    public Event addGame(Game game){
        this.games.add(game);
        return this;
    }
    
}
