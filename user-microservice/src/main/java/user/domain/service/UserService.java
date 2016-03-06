package user.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.domain.User;
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
    
    public User addUser(User user){
        return this.userRepository.add(User.createUser(user.getNickname(),user.getEmail()));
    }

    public User find(String id){
        return this.userRepository.findOne(id);
    }

}
