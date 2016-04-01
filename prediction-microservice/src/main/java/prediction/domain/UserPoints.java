package prediction.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Claudio E. de Oliveira on 22/03/16.
 */
@Data
public class UserPoints {
    
    private String gameId;
    
    private String userId;
    
    private Integer pointsEarned;

    UserPoints(){}

    public UserPoints(String gameId, String userId, Integer pointsEarned) {
        this.gameId = gameId;
        this.userId = userId;
        this.pointsEarned = pointsEarned;
    }

}
