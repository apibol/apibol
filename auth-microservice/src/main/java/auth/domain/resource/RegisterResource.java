package auth.domain.resource;

import auth.domain.Credential;
import auth.domain.resource.response.CredentialResponse;
import auth.domain.resource.vo.CredentialVO;
import auth.domain.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Register resources
 *
 * @author Claudio E. de Oliveira on on 21/04/16.
 */
@RestController
@RequestMapping("/register")
public class RegisterResource {

    @Autowired
    private CredentialService credentialService;

    @RequestMapping(value = "/owner",method = RequestMethod.POST)
    public ResponseEntity<CredentialResponse> newOwner(@RequestBody CredentialVO credential){
        Credential owner = this.credentialService.createOwner(credential);
        return new ResponseEntity<>(CredentialResponse.newResponse(owner), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/maintainer",method = RequestMethod.POST)
    public ResponseEntity<CredentialResponse> newMaintainer(@RequestBody CredentialVO credential){
        Credential maintainer = this.credentialService.createMaintainer(credential);
        return new ResponseEntity<>(CredentialResponse.newResponse(maintainer), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ResponseEntity<CredentialResponse> newUser(@RequestBody CredentialVO credential){
        Credential user = this.credentialService.createUser(credential);
        return new ResponseEntity<>(CredentialResponse.newResponse(user), HttpStatus.CREATED);
    }

}
