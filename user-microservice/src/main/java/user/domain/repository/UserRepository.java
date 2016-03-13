package user.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import user.domain.User;
import user.infra.data.UserMapper;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@Repository
public class UserRepository {

    private static final String INSERT_USER = "INSERT INTO users (id,nickname,email) VALUES (?,?,?)";

    private static final String BY_ID = "SELECT * FROM users WHERE id = ?";
    
    private static final String BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Add user on repository 
     * @param user
     * @return
     */
    public User add(User user) {
        jdbcTemplate.update(INSERT_USER, user.getId(), user.getNickname(), user.getEmail());
        return user;
    }

    /**
     * Retrieve user from Id 
     * @param id
     * @return
     */
    public User findOne(String id) {
        return jdbcTemplate.queryForObject(BY_ID, new Object[]{id}, new UserMapper());
    }

    /**
     * Retrieve user from email
     * @param email
     * @return
     */
    public User findByEmail(String email) {
        return jdbcTemplate.queryForObject(BY_EMAIL, new Object[]{email}, new UserMapper());
    }

}
