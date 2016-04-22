package auth.infra.mapper;

import auth.domain.Credential;
import auth.domain.Scope;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Mapper for database scope credential row into Scope Object
 *
 * @author Claudio E. de Oliveira on 06/03/16.
 */
public class CredentialScopeMapper implements RowMapper<Scope> {
    
    @Override
    public Scope mapRow(ResultSet resultSet, int i) throws SQLException {
        String scope = resultSet.getString("scope");
        return Scope.create(scope);
    }

}
