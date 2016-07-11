package predictor.domain.resource.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Claudio E. de Oliveira on on 10/05/16.
 */
@Data
public class HashDTO {

    @NotEmpty(message = "hash cannot be null")
    private String hash;

}
