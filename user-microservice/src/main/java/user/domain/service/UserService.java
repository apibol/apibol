package user.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.domain.User;
import user.domain.exception.UserAlreadyExists;
import user.domain.repository.UserRepository;

import java.util.List;
import java.util.Objects;

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
    public User addUser(User user){
        if(checkUserAlreadyExists(user.getEmail())){
            return this.userRepository.add(User.createUser(user.getNickname(),user.getEmail()));
        }
        return User.createNullUser();
    }

    /**
     * Retrieve user by Id 
     * @param id
     * @return
     */
    public User find(String id){
        return this.userRepository.findOne(id);
    }

    /**
     * Check user if exists
     * @param email
     * @return
     */
    private boolean checkUserAlreadyExists(String email){
        User byEmail = this.userRepository.findByEmail(email);
        if(Objects.nonNull(byEmail)){
            throw new UserAlreadyExists(email);
        }
        return false;
    }

}
