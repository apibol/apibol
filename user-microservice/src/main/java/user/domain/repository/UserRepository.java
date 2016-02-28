package user.domain.repository;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.domain.User;

import java.util.UUID;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@Service
public class UserRepository {

    private final Gson gson;

    @Autowired
    public UserRepository(Gson gson) {
        this.gson = gson;
    }

    public User addUser(String email, String nickname) {
        String id = UUID.randomUUID().toString();
        User newUser = User.createUser(nickname, email, id);
        return newUser;
    }

    public User getUser(String email) {
        String userJson = "";
        return this.gson.fromJson(userJson, User.class);
    }

}
