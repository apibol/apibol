package user.domain;

import java.util.UUID;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
public class User {

    String id;

    String email;

    String nickname;

    protected User(String nickname, String email, String id) {
        this.nickname = nickname;
        this.email = email;
        this.id = id;
    }

    public static User createUser(String nickname, String email) {
        return new User(nickname, email, UUID.randomUUID().toString());
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

}
