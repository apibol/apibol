package prediction.domain.resource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;
import prediction.domain.BattlePrediction;
import prediction.domain.User;

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
    public BattlePrediction toDomain(User owner,String predictorId,String gameId) {
        return BattlePrediction.newBattlePrediction(gameId, predictorId, this.playerOneResult, this.playerTwoResult, owner);
    }

}
