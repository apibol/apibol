package user.domain.repository;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import user.domain.User;
import user.domain.exception.UserNotFoundByEmail;
import user.domain.exception.UserNotFoundById;
import user.domain.exception.UserNotFoundByNickname;
import user.infra.data.UserMapper;

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

    /**
     * The users cache Key: nickname Value: user
     */
    private final Cache<String, User> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(24L, TimeUnit.HOURS).build();

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
            log.error(String.format("User %s not found by id", id),e);
            throw new UserNotFoundById(id);
        }
    }

    /**
     * Retrieve user from email
     *
     * @param email - the email
     * @return
     */
    public User findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(BY_EMAIL, new Object[]{email}, new UserMapper());
            addOrUpdateInCache(user);
            return user;
        } catch (EmptyResultDataAccessException e) {
            log.error(String.format("User %s not found by email", email), e);
            throw new UserNotFoundByEmail(email);
        }
    }

    /**
     * Retrieve user from nickname
     *
     * @param nickname - the nickname
     * @return
     */
    public User findByNickname(String nickname) {
        final User cachedUser = getIfPresentInCache(nickname);
        if (Objects.nonNull(cachedUser)) {
            return cachedUser;
        }
        try {
            User user = jdbcTemplate.queryForObject(BY_NICKNAME, new Object[]{nickname}, new UserMapper());
            addOrUpdateInCache(user);
            return user;
        } catch (EmptyResultDataAccessException e) {
            log.error(String.format("User %s not found by nickname", nickname), e);
            throw new UserNotFoundByNickname(nickname);
        }
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
        } catch (UserNotFoundByEmail e) {
            log.info(String.format("User with email %s", email), e);
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
        } catch (UserNotFoundByNickname e) {
            log.info(String.format("User with nickname %s", nickname), e);
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
    private void addOrUpdateInCache(User user) {
        this.cache.put(user.getNickname(), user);
    }

    /**
     * Get if user present in cache
     *
     * @param nickname - the nickname
     * @return
     */
    private User getIfPresentInCache(String nickname) {
        return this.cache.getIfPresent(nickname);
    }

}
