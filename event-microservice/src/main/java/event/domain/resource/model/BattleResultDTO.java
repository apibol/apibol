package event.domain.resource.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents battle result
 * @author Claudio E. de Oliveira on 22/03/16.
 */
@Data
public class BattleResultDTO {

    @NotEmpty(message = "player one result cannot be null")
    private String playerOneResult;

    @NotEmpty(message = "player two result cannot be null")
    private String playerTwoResult;
    
}
