package predictor.domain.resource.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Claudio E. de Oliveira on 10/03/16.
 */
@Data
public class PredictorDTO {

    @NotEmpty(message = "event id cannot be null")
    private String eventId;

    @NotEmpty(message = "user id cannot be null")
    private String userId;
    
}
