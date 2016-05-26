package prediction.domain;

import domain.Participant;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Data
public abstract class Prediction {
    
    protected String id;

    protected String gameId;

    protected Participant owner;
    
    protected String predictor;

}
