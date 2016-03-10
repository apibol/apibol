package championship.domain;

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
@Document(collection = "championship")
public class Championship {
    
    @Id
    private String id;
    
    private String name;
    
    private Period period;
    
    private Boolean open = Boolean.FALSE;
    
    private List<String> participants = new ArrayList<>();
    
    private List<Game> games = new ArrayList<>();
    
    private User owner;

    private Championship(String id, String name, Period period, Boolean open, User owner) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.open = open;
        this.owner = owner;
    }

    public static Championship newChampionship(String id, String name, Period period, Boolean open, User owner) {
        return new Championship(id, name, period, open, owner);
    }

    public Championship addGame(Game game){
        this.games.add(game);
        return this;
    }
    
}
