package event.domain.service;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * The battle result
 *
 * @author Claudio E. de Oliveira on 22/03/16.
 */
@Data
public class BattleResult {

    private String eventId;

    private String gameId;

    private String playerOneResult;

    private String playerTwoResult;

    /**
     * Default Constructor
     */
    BattleResult() {
    }

    /**
     * Constructor
     *
     * @param eventId
     * @param gameId
     * @param playerOneResult
     * @param playerTwoResult
     */
    private BattleResult(String eventId, String gameId, String playerOneResult, String playerTwoResult) {
        this.eventId = eventId;
        this.gameId = gameId;
        this.playerOneResult = playerOneResult;
        this.playerTwoResult = playerTwoResult;
    }

    /**
     * Factory method
     *
     * @param eventId
     * @param gameId
     * @param playerOneResult
     * @param playerTwoResult
     * @return
     */
    public static BattleResult createNew(String eventId, String gameId, String playerOneResult, String playerTwoResult) {
        return new BattleResult(eventId, gameId, playerOneResult, playerTwoResult);
    }

}
