package predictor.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Participant not found or invalid
 *
 * @author Claudio E. de Oliveira on 12/03/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Participant not found or invalid")
public class InvalidParticipant extends RuntimeException {

    @Getter
    private final String userId;

    public InvalidParticipant(String userId) {
        this.userId = userId;
    }

}
