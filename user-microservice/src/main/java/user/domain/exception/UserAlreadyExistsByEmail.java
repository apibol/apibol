package user.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Indicates if user exists by email
 * *
 *
 * @author Claudio E. de Oliveira on 13/03/16.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User with email already exists")
public class UserAlreadyExistsByEmail extends RuntimeException {

    @Getter
    private final String email;

    public UserAlreadyExistsByEmail(String email) {
        this.email = email;
    }

}
