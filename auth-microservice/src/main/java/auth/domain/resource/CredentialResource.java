package auth.domain.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Claudio E. de Oliveira on on 10/04/16.
 */
@RestController
@RequestMapping("/credential")
public class CredentialResource {

    @RequestMapping(method = RequestMethod.GET)
    public Principal user(Principal user) {
        return user;
    }

}
