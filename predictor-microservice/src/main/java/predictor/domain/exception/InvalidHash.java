package predictor.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Invalid hash
 *
 * @author Claudio E. de Oliveira on 12/03/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid hash try again")
public class InvalidHash extends RuntimeException {

    @Getter
    private final String participantId;

    public InvalidHash(String participantId) {
        this.participantId = participantId;
    }

}
