package predictor.domain.resource;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Claudio E. de Oliveira on 10/03/16.
 */
@Data
public class JoinPredictorDTO {

    @NotEmpty(message = "user id cannot be null")
    private String userId;
    
}
