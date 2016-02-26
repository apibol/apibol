package user.domain;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
public class User {
    
    private final String id;
    
    private final String email;

    private final String nickname;

    private User(String nickname, String email, String id) {
        this.nickname = nickname;
        this.email = email;
        this.id = id;
    }

    public static User createUser(String nickname, String email, String id) {
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
}
