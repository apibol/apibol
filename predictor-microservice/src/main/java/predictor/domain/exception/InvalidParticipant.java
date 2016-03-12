package predictor.domain.exception;

import lombok.Getter;

/**
 * @author Claudio E. de Oliveira on 12/03/16.
 */

public class InvalidParticipant extends RuntimeException {

    @Getter
    private final String userId;

    public InvalidParticipant(String userId) {
        this.userId = userId;
    }

}
