package user.domain.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.domain.User;
import user.domain.service.UserService;

import java.util.List;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@RestController
@RequestMapping(value = "/user")
public class UserResource {
    
    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> all(){
        List<User> users = this.userService.all();
        return new ResponseEntity<>(users,HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<User> find(@PathVariable("id")String id){
        User user = this.userService.find(id);
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user){
        User savedUser = this.userService.addUser(user);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{email}")
    public ResponseEntity<User> getUser(@PathVariable("email")String email){
        User user = this.userService.getUser(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
}
