package prediction.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Data @EqualsAndHashCode(callSuper = false)
public class BattlePrediction extends Prediction {
    
    private String playerOneResult;

    private String playerTwoResult;
    
}
