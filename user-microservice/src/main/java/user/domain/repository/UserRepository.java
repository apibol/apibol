package user.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import user.domain.User;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@Repository
public class UserRepository{
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> all(){
        return new ArrayList<>();
    }
    
}
