package prediction.domain.resource.model;

import lombok.Data;

/**
 * @author Claudio E. de Oliveira on 19/03/16.
 */
@Data
public class BattlePredictionDTO {
    
    private String predictorId;
    
    private String gameId;

    private String playerOneResult;

    private String playerTwoResult;
    
}
