package predictor.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Participant not present in event
 *
 * @author Claudio E. de Oliveira on 20/03/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Participant is not present in predictor")
public class ParticipantNotInPredictor extends RuntimeException {

    @Getter
    private final String participantId;

    @Getter
    private final String predictorId;

    public ParticipantNotInPredictor(String participantId, String predictorId) {
        this.participantId = participantId;
        this.predictorId = predictorId;
    }

}
