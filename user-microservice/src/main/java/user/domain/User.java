package user.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.UUID;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@Data
public class User implements AbstractNullObject {

    String id;

    @NotEmpty(message = "email cannot be null")
    String email;

    @NotEmpty(message = "nickname cannot be null")
    String nickname;

    User() {
    }

    protected User(String nickname, String email, String id) {
        this.nickname = nickname;
        this.email = email;
        this.id = id;
    }

    public static User createUser(String nickname, String email) {
        return new User(nickname, email, UUID.randomUUID().toString());
    }

    public static NullUser createNullUser() {
        return new NullUser();
    }
    
    public static User fromDatabase(String id,String nickname, String email) {
        return new User(nickname, email, id);
    }

    @Override
    public boolean isNil() {
        return false;
    }
    
}
