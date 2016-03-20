package prediction.domain.resource.model;

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

    // TODO Refactor to get user from OAuth sub
    @Transient
    private User owner;

    @Transient
    private String predictorId;

    @NotEmpty
    private String gameId;

    @NotEmpty
    private String playerOneResult;

    @NotEmpty
    private String playerTwoResult;

    @NotEmpty
    private String userId;

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
