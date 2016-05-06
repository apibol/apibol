package prediction.domain.exception;

import lombok.Getter;

/**
 * Invalid User exception
 *
 * @author Claudio E. de Oliveira on 12/03/16.
 */

public class InvalidUser extends RuntimeException {

    @Getter
    private final String userId;

    public InvalidUser(String userId) {
        this.userId = userId;
    }

}
