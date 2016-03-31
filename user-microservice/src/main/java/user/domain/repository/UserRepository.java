package user.domain.repository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import user.domain.User;
import user.domain.exception.UserNotFound;
import user.infra.data.UserMapper;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@Repository
@Log4j2
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
     *
     * @param user
     * @return
     */
    public User add(User user) {
        jdbcTemplate.update(INSERT_USER, user.getId(), user.getNickname(), user.getEmail());
        return user;
    }

    /**
     * Retrieve user from Id
     *
     * @param id
     * @return
     */
    public User findOne(String id) {
        try {
            return jdbcTemplate.queryForObject(BY_ID, new Object[]{id}, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            log.error(String.format("User %s not found", id));
            throw new UserNotFound(id);
        }
    }

    /**
     * Retrieve user from email
     *
     * @param email
     * @return
     */
    public User findByEmail(String email) {
        return jdbcTemplate.queryForObject(BY_EMAIL, new Object[]{email}, new UserMapper());
    }

    /**
     * Check user if exists
     * @param email
     * @return
     */
    public boolean checkIfUserExists(String email) {
        try {
            return Objects.nonNull(this.findByEmail(email));
        } catch (EmptyResultDataAccessException e) {
            log.info(String.format("User with email %s", email));
            return false;
        }
    }


}
