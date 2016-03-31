package user.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User not found Exception
 * @author Claudio E. de Oliveira on 20/03/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFound extends RuntimeException {

    @Getter
    private final String userId;

    public UserNotFound(String userId) {
        this.userId = userId;
    }

}
