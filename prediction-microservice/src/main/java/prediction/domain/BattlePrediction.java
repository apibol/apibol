package prediction.domain;

import domain.Participant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BattlePrediction extends Prediction {

    private String playerOneResult;

    private String playerTwoResult;

    BattlePrediction() {
    }

    /**
     * Private constructor to use in Factory Method
     *
     * @param id
     * @param predictor
     * @param gameId
     * @param playerOneResult
     * @param playerTwoResult
     * @param owner
     */
    private BattlePrediction(String id, String predictor, String gameId, String playerOneResult, String playerTwoResult, Participant owner) {
        this.id = id;
        this.gameId = gameId;
        this.playerOneResult = playerOneResult;
        this.playerTwoResult = playerTwoResult;
        this.owner = owner;
        this.predictor = predictor;
    }

    /**
     * Factory Method
     *
     * @param gameId
     * @param predictor
     * @param playerOneResult
     * @param playerTwoResult
     * @param owner
     * @return
     */
    public static BattlePrediction newBattlePrediction(String gameId, String predictor, String playerOneResult, String playerTwoResult, Participant owner) {
        return new BattlePrediction(UUID.randomUUID().toString(), predictor, gameId, playerOneResult, playerTwoResult, owner);
    }

}
