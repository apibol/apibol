package event.domain.service;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Claudio E. de Oliveira on 22/03/16.
 */
@Data
@Builder
public class BattleResult {

    private String eventId;

    private String gameId;
    
    private String playerOneResult;

    private String playerTwoResult;
    
}
