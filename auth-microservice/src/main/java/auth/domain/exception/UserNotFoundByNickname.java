package auth.domain.exception;

import lombok.Getter;

/**
 * User not found Exception  by nickname
 *
 * @author Claudio E. de Oliveira on 20/03/16.
 */
public class UserNotFoundByNickname extends RuntimeException {

    @Getter
    private final String nickname;

    public UserNotFoundByNickname(String nickname) {
        this.nickname = nickname;
    }

}
