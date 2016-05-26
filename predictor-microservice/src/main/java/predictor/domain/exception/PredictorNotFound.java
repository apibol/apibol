package predictor.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Predictor not found
 *
 * @author Claudio E. de Oliveira on on 26/05/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Predictor not found")
public class PredictorNotFound extends RuntimeException {

    @Getter
    private final String predictorId;

    public PredictorNotFound(String predictorId) {
        this.predictorId = predictorId;
    }

}
