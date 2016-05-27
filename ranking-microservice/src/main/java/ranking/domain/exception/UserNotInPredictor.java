package ranking.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User not in predictor exception
 *
 * @author Claudio E. de Oliveira on 27/05/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "User is not in requested predictor")
public class UserNotInPredictor extends RuntimeException {

    @Getter
    private final String predictorId;

    @Getter
    private final String participantId;

    public UserNotInPredictor(String predictorId,String participantId) {
        this.predictorId = predictorId;
        this.participantId = participantId;
    }

}
