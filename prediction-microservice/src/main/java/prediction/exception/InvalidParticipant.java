package prediction.exception;

import lombok.Getter;

/**
 * Invalid Participant Exception
 * @author Claudio E. de Oliveira on 12/03/16.
 */
public class InvalidParticipant extends RuntimeException{

    @Getter
    private final String participantId;

    public InvalidParticipant(String participantId) {
        this.participantId = participantId;
    }

}
