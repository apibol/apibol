package predictor.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Participant is not invited exception
 *
 * @author Claudio E. de Oliveira on on 25/04/16.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Participant is not invited")
public class ParticipantNotInvited extends RuntimeException {

    @Getter
    private final String participantId;

    @Getter
    private final String predictorId;

    public ParticipantNotInvited(String participantId, String predictorId) {
        this.participantId = participantId;
        this.predictorId = predictorId;
    }

}
