package prediction.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User is not predictor participant
 *
 * @author Claudio E. de Oliveira on on 05/05/16.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "User is not predictor participant")
public class UserIsNotInPredictor extends RuntimeException{

    @Getter
    private final String predictor;

    @Getter
    private final String userId;

    public UserIsNotInPredictor(String predictor, String userId) {
        this.predictor = predictor;
        this.userId = userId;
    }

}
