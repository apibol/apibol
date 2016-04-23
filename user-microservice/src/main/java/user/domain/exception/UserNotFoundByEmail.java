package user.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User not found Exception  by email
 *
 * @author Claudio E. de Oliveira on 20/03/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundByEmail extends RuntimeException {

    @Getter
    private final String email;

    public UserNotFoundByEmail(String email) {
        this.email = email;
    }

}
