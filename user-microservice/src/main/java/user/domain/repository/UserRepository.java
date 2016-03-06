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

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User add(User user) {
        jdbcTemplate.update(INSERT_USER, user.getId(), user.getNickname(), user.getEmail());
        return user;
    }

    public User findOne(String id) {
        return jdbcTemplate.queryForObject(BY_ID, new Object[]{id}, new UserMapper());
    }

}
