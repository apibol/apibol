package event.domain.resource.model;

import lombok.Data;

/**
 * Represents battle result
 * @author Claudio E. de Oliveira on 22/03/16.
 */
@Data
public class BattleResultDTO {
    
    private String playerOneResult;

    private String playerTwoResult;
    
}
