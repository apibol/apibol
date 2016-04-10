package predictor.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Predictor not found
 *
 * @author Claudio E. de Oliveira on 10/04/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Predictor not found")
public class InvalidPredictor extends RuntimeException {

    @Getter
    private final String predictorId;

    public InvalidPredictor(String predictorId) {
        this.predictorId = predictorId;
    }

}
