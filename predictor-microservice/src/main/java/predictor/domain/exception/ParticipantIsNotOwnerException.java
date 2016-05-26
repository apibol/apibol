package predictor.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Participant is not predictor owner
 *
 * @author Claudio E. de Oliveira on on 26/05/16.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Participant is not predictor owner")
public class ParticipantIsNotOwnerException extends RuntimeException {

    @Getter
    private final String predictorId;

    @Getter
    private final String userId;

    public ParticipantIsNotOwnerException(String predictorId,String userId) {
        this.predictorId = predictorId;
        this.userId = userId;
    }

}
