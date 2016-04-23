package user.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.domain.User;
import user.domain.exception.UserAlreadyExistsByEmail;
import user.domain.exception.UserAlreadyExistsByNickname;
import user.domain.repository.UserRepository;

import java.util.List;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Add user on repository
     *
     * @param user
     * @return
     */
    public User addUser(User user) {
        if (!checkUserAlreadyExists(user.getEmail(),user.getNickname())) {
            return this.userRepository.add(User.createUser(user.getNickname(), user.getEmail()));
        }
        return User.createNullUser();
    }

    /**
     * Retrieve user by Id
     *
     * @param id
     * @return
     */
    public User find(String id) {
        return this.userRepository.findOne(id);
    }

    /**
     * Check user if exists
     *
     * @param email    - the email
     * @param nickname - the nickname
     * @return
     */
    private boolean checkUserAlreadyExists(String email, String nickname) {
        boolean existsByEmail = this.userRepository.checkIfUserExistsByEmail(email);
        boolean existsByNickname = this.userRepository.checkIfUserExistsByNickname(nickname);
        if (existsByEmail) {
            throw new UserAlreadyExistsByEmail(email);
        }
        if (existsByNickname) {
            throw new UserAlreadyExistsByNickname(nickname);
        }
        return false;
    }

    /**
     * Find All Users
     *
     * @return
     */
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    /**
     * Retrieve user by nickname
     *
     * @param nickname - the nickname
     * @return
     */
    public User findByNickname(String nickname) {
        return this.userRepository.findByNickname(nickname);
    }

}
