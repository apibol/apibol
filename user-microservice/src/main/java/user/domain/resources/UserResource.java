package user.domain.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.domain.User;
import user.domain.service.UserService;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserResource {
    
    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping(value = "/{email}")
    public User getUser(@PathVariable("email")String email){
        return this.userService.getUser(email);
    }
    
}
