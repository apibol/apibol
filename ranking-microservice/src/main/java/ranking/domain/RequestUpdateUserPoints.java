package ranking.domain;

import lombok.Data;

/**
 * User earned points 
 * @author Claudio E. de Oliveira on 22/03/16.
 */
@Data
public class RequestUpdateUserPoints {
    
    private String predictorId;
    
    private String userId;
    
    private Integer pointsEarned;
    
}
