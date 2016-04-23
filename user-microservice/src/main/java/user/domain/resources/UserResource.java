package user.domain.resources;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import user.domain.User;
import user.domain.service.UserService;

import java.util.List;

/**
 * REST API for user entity
 *
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@RestController
@RequestMapping(value = "/")
@Api(value = "/user", description = "Operations about users")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> find() {
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> find(@PathVariable("id") String id) {
        User user = this.userService.find(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public ResponseEntity<User> findByNickname(@RequestParam(value = "nickname", required = true) String nickname) {
        User user = this.userService.findByNickname(nickname);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@Validated @RequestBody User user) {
        User savedUser = this.userService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

}
