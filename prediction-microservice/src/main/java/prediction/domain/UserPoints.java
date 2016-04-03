package prediction.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * It represent the points earned by user.
 * @author Claudio E. de Oliveira on 22/03/16.
 */
@Data
public class UserPoints {
    
    private String gameId;
    
    private String userId;
    
    private Integer pointsEarned;

    private String nickname;

    UserPoints(){}

    public UserPoints(String gameId, String userId, Integer pointsEarned,String nickname) {
        this.gameId = gameId;
        this.userId = userId;
        this.pointsEarned = pointsEarned;
        this.nickname = nickname;
    }

}
