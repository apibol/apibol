package user.domain.repository;

import com.google.gson.Gson;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.ast.Db;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.rethinkdb.net.Connection;
import user.domain.User;

import java.util.UUID;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@Service
public class UserRepository {

    private final Db database;
    
    private final Connection connection;
    
    private final Gson gson;

    @Autowired
    public UserRepository(@Qualifier("database")Db database, @Qualifier("dbConnector")Connection connection, Gson gson) {
        this.database = database;
        this.connection = connection;
        this.gson = gson;
    }

    public User addUser(String email, String nickname) {
        String id = UUID.randomUUID().toString();
        User newUser = User.createUser(nickname, email, id);
        this.database.table("user").insert(newUser).run(this.connection);
        return newUser;
    }

    public User getUser(String email) {
        String userJson = "";
        return this.gson.fromJson(userJson, User.class);
    }

}
