package prediction.domain;

import lombok.Data;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Data
public abstract class Prediction {
    
    protected String id;

    protected String gameId;

    protected User owner;
    
    protected String predictor;
    
}
