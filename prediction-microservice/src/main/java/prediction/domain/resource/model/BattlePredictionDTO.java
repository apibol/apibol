package prediction.domain.resource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;
import prediction.domain.BattlePrediction;
import prediction.domain.User;

/**
 * @author Claudio E. de Oliveira on 19/03/16.
 */
@Data
public class BattlePredictionDTO implements DomainConverter<BattlePrediction> {

    @JsonIgnore
    private User owner;

    @NotEmpty(message = "predictor id cannot be null")
    private String predictorId;

    @NotEmpty(message = "player game result cannot be null")
    private String gameId;

    @NotEmpty(message = "player two result cannot be null")
    private String playerOneResult;

    @NotEmpty(message = "player one result cannot be null")
    private String playerTwoResult;

    @Override
    public BattlePrediction toDomain() {
        return BattlePrediction.newBattlePrediction(this.gameId, this.predictorId, this.playerOneResult, this.playerTwoResult, this.owner);
    }

    /**
     * Assign owner to prediction
     *
     * @param owner
     * @return
     */
    public BattlePredictionDTO assignOwner(User owner) {
        this.owner = owner;
        return this;
    }

    /**
     * Assign predictorId to prediction
     *
     * @param predictorId
     * @return
     */
    public BattlePredictionDTO assignPredictor(String predictorId) {
        this.predictorId = predictorId;
        return this;
    }

}
