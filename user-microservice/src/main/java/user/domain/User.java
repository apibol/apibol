package user.domain;

import java.util.UUID;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
public class User implements AbstractNullObject {

    String id;

    String email;

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

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public boolean isNil() {
        return false;
    }
    
}
