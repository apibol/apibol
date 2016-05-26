package prediction.domain.resource.model;

import domain.Participant;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import prediction.domain.BattlePrediction;

/**
 * Battle Prediction DTO
 *
 * @author Claudio E. de Oliveira on 19/03/16.
 */
@Data
public class BattlePredictionDTO {

    @NotEmpty(message = "player two result cannot be null")
    private String playerOneResult;

    @NotEmpty(message = "player one result cannot be null")
    private String playerTwoResult;

    /**
     * Convert to domain entity
     *
     * @param owner
     * @param predictorId
     * @param gameId
     * @return
     */
    public BattlePrediction toDomain(Participant owner, String predictorId, String gameId) {
        return BattlePrediction.newBattlePrediction(gameId, predictorId, this.playerOneResult, this.playerTwoResult, owner);
    }

}
