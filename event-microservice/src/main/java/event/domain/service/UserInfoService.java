package event.domain.service;

import event.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Claudio E. de Oliveira on 06/03/16.
 */
@Service
public class UserInfoService {

    @Autowired @LoadBalanced
    private RestTemplate restTemplate;

    @Value("${usermicroservice.user-info}")
    private String url;
    
    public User getUserInfo(String userId){
        return this.restTemplate.getForObject(this.url+userId, User.class);
    }
        
}
