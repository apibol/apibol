package predictor.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Predictors not found
 *
 * @author Claudio E. de Oliveira on on 26/05/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No predictors found")
public class NoPredictorsFound extends RuntimeException {

    @Getter
    private final String userId;

    public NoPredictorsFound(String userId) {
        this.userId = userId;
    }

}
