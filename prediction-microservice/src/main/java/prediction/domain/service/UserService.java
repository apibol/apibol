package prediction.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import prediction.domain.User;
import prediction.domain.exception.InvalidUser;

/**
 * @author Claudio E. de Oliveira on 06/03/16.
 */
@Service
public class UserService {

    @Autowired @LoadBalanced
    private RestTemplate restTemplate;

    @Value("${services.user.info}")
    private String url;

    /**
     * Get user information
     * @param userId
     * @return
     */
    public User getUserInfo(String userId){
        ResponseEntity<User> response = this.restTemplate.getForEntity(this.url + userId, User.class);
        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }else {
            throw new InvalidUser(userId);
        }
    }
        
}
