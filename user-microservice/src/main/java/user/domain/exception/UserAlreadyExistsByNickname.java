package user.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Indicates if user exists by nickname
 * *
 *
 * @author Claudio E. de Oliveira on 23/04/16.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User with nickname already exists")
public class UserAlreadyExistsByNickname extends RuntimeException {

    @Getter
    private final String nickname;

    public UserAlreadyExistsByNickname(String nickname) {
        this.nickname = nickname;
    }

}
