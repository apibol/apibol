package user.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.UUID;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@Document
public class User {

    @Id
    String id;

    @Field
    String email;

    @Field
    String nickname;

    protected User() {
    }

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
