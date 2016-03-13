package user.domain.exception;

import lombok.Getter;

/**
 * Indicates if user exists
 * *
 * @author Claudio E. de Oliveira on 13/03/16.
 */
public class UserAlreadyExists extends RuntimeException{

    @Getter
    private final String email;

    public UserAlreadyExists(String email) {
        this.email = email;
    }
    
}
