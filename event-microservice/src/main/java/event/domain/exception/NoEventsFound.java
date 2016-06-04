package event.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * No events for user
 *
 * @author Claudio E. de Oliveira on 04/06/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No events found")
public class NoEventsFound extends RuntimeException {

    @Getter
    private final String userId;

    public NoEventsFound(String userId) {
        this.userId = userId;
    }

}
