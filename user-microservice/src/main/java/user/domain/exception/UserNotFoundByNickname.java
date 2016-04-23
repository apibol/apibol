package user.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User not found Exception  by nickname
 *
 * @author Claudio E. de Oliveira on 20/03/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundByNickname extends RuntimeException {

    @Getter
    private final String nickname;

    public UserNotFoundByNickname(String nickname) {
        this.nickname = nickname;
    }

}
