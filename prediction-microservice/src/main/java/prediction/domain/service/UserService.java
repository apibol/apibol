package prediction.domain.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import prediction.domain.User;
import prediction.domain.exception.InvalidUser;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Claudio E. de Oliveira on 06/03/16.
 */
@Service
@RefreshScope
@Log4j2
public class UserService {

    @Autowired @LoadBalanced
    private RestTemplate restTemplate;

    @Value("${services.user.info}")
    private String url;

    private final Cache<String,User> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(24L, TimeUnit.HOURS).build();

    @HystrixCommand(fallbackMethod = "getParticipantInCache")
    public User getUserInfo(String userId){
        log.info("[REQUEST-PARTICIPANT-INFO] Request event info ");
        ResponseEntity<User> response = this.restTemplate.getForEntity(this.url + userId, User.class);
        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }else {
            log.error(String.format("[REQUEST-PARTICIPANT-INFO] HTTP - Error on retrieve user %s information", userId));
            throw new InvalidUser(userId);
        }
    }

    /**
     * Retrieve user from cache
     *
     * @param userId
     * @return
     */
    public User getParticipantInCache(String userId){
        User User = cache.getIfPresent(userId);
        if(Objects.isNull(User)){
            log.error(String.format("[REQUEST-PARTICIPANT-INFO] CACHE - Error on retrieve user %s information", userId));
            throw new InvalidUser(userId);
        }
        return User;
    }
        
}
