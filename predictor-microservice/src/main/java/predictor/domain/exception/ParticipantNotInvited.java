package predictor.domain.exception;

import lombok.Getter;

/**
 * @author Claudio E. de Oliveira on on 25/04/16.
 */
public class ParticipantNotInvited extends RuntimeException {

    @Getter
    private final String participantId;

    @Getter
    private final String predictorId;

    public ParticipantNotInvited(String participantId,String predictorId) {
        this.participantId = participantId;
        this.predictorId = predictorId;
    }

}
