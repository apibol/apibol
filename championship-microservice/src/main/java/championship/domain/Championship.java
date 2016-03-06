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
    
    public Championship addGame(Game game){
        this.games.add(game);
        return this;
    }
    
}
