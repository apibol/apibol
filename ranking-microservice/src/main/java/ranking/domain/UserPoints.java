package ranking.domain;

import lombok.Data;

/**
 * Total points by user
 * @author Claudio E. de Oliveira on 23/03/16.
 */
@Data
public class UserPoints {
    
    private String predictorId;
    
    private String userId;
    
    private Integer points;
    
}
