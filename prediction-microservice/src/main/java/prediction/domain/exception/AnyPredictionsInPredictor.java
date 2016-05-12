package prediction.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Any predictions in predictor
 *
 * @author Claudio E. de Oliveira on on 11/05/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Predictions not found")
public class AnyPredictionsInPredictor extends RuntimeException {

    @Getter
    private final String userId;

    @Getter
    private final String predictorId;

    public AnyPredictionsInPredictor(String userId, String predictorId) {
        this.userId = userId;
        this.predictorId = predictorId;
    }

}
