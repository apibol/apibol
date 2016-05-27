package auth.domain.service;

import auth.domain.Credential;
import auth.domain.resource.vo.CredentialVO;
import domain.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Register user service
 *
 * @author Claudio E. de Oliveira on on 03/05/16.
 */
@Service
public class RegisterUserService {

    private final RestTemplate restTemplate;

    private final String url;

    @Autowired
    public RegisterUserService(RestTemplate restTemplate,@Value("${services.user.new}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    /**
     * Register new user in user microservice
     *
     * @param credential
     * @return
     */
    public NewUser registerNewUser(Credential credential){
        NewUser newUser = new NewUser();
        newUser.setNickname(credential.getNickname());
        newUser.setEmail(credential.getEmail());
        final ResponseEntity<NewUser> createdUser = this.restTemplate.postForEntity(this.url, newUser, NewUser.class);
        return createdUser.getBody();
    }

}
