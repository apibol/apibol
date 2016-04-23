package user.domain.repository;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
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
import java.util.concurrent.TimeUnit;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@Repository
@Log4j2
public class UserRepository {

    private static final String INSERT_USER = "INSERT INTO users (id,nickname,email) VALUES (?,?,?)";

    private static final String BY_ID = "SELECT * FROM users WHERE id = ?";

    private static final String BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    private static final String BY_NICKNAME = "SELECT * FROM users WHERE nickname = ?";

    private static final String ALL_USERS = "SELECT * FROM users";

    private final JdbcTemplate jdbcTemplate;

    private final Cache<String,User> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(24L, TimeUnit.HOURS).build();

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
        addOrUpdateInCache(user);
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
            User user = jdbcTemplate.queryForObject(BY_ID, new Object[]{id}, new UserMapper());
            addOrUpdateInCache(user);
            return user;
        } catch (EmptyResultDataAccessException e) {
            log.error(String.format("User %s not found", id));
            throw new UserNotFound(id);
        }
    }

    /**
     * Retrieve user from email
     *
     * @param email - the email
     * @return
     */
    public User findByEmail(String email) {
        User user = jdbcTemplate.queryForObject(BY_EMAIL, new Object[]{email}, new UserMapper());
        addOrUpdateInCache(user);
        return user;
    }

    /**
     * Retrieve user from nickname
     *
     * @param nickname - the nickname
     * @return
     */
    public User findByNickname(String nickname) {
        User user = jdbcTemplate.queryForObject(BY_NICKNAME, new Object[]{nickname}, new UserMapper());
        addOrUpdateInCache(user);
        return user;
    }

    /**
     * Check user if exists by email
     *
     * @param email - the email
     * @return
     */
    public boolean checkIfUserExistsByEmail(String email) {
        try {
            return Objects.nonNull(this.findByEmail(email));
        } catch (EmptyResultDataAccessException e) {
            log.info(String.format("User with email %s", email));
            return false;
        }
    }

    /**
     * Check user if exists by nickname
     *
     * @param nickname - the nickname
     * @return
     */
    public boolean checkIfUserExistsByNickname(String nickname) {
        try {
            return Objects.nonNull(this.findByNickname(nickname));
        } catch (EmptyResultDataAccessException e) {
            log.info(String.format("User with nickname %s", nickname));
            return false;
        }
    }


    /**
     * Find All users
     *
     * @return
     */
    public List<User> findAll() {
        return this.jdbcTemplate.query(ALL_USERS, new Object[]{}, new UserMapper());
    }

    /**
     * Update user information in cache
     *
     * @param user
     */
    private void addOrUpdateInCache(User user){
        this.cache.put(user.getId(),user);
    }

}
