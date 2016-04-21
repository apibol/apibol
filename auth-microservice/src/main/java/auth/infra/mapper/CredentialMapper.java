package auth.infra.mapper;

import auth.domain.Credential;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapper for database credential row into Credential Object
 *
 * @author Claudio E. de Oliveira on 06/03/16.
 */
public class CredentialMapper implements RowMapper<Credential> {
    
    @Override
    public Credential mapRow(ResultSet resultSet, int i) throws SQLException {
        String id = resultSet.getString("id");
        String nickname = resultSet.getString("nickname");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return Credential.fromDatabase(id,email,nickname,password);
    }

}
