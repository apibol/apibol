package user.infra.data;

import org.springframework.jdbc.core.RowMapper;
import user.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Claudio E. de Oliveira on 06/03/16.
 */
public class UserMapper implements RowMapper<User> {
    
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        String id = resultSet.getString("id");
        String nickname = resultSet.getString("nickname");
        String email = resultSet.getString("email");
        return User.fromDatabase(id,nickname,email);
    }
}
