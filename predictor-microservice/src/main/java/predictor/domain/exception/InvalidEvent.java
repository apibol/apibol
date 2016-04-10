package predictor.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Invalid event or not found
 *
 * @author Claudio E. de Oliveira on 12/03/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Event not found or invalid")
public class InvalidEvent extends RuntimeException {

    @Getter
    private final String eventId;

    public InvalidEvent(String eventId) {
        this.eventId = eventId;
    }

}
