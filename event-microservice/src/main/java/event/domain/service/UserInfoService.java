package event.domain.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import event.domain.User;
import event.domain.exception.UserNotFound;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Claudio E. de Oliveira on 06/03/16.
 */
@Service
@RefreshScope
@Log4j2
public class UserInfoService {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @Value("${usermicroservice.user-info}")
    private String url;

    private final Cache<String,User> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(24L, TimeUnit.HOURS).build();

    @HystrixCommand(fallbackMethod = "getCachedUser")
    public User getUserInfo(String userId) {
        ResponseEntity<User> response = this.restTemplate.getForEntity(this.url + userId, User.class);
        User body = response.getBody();
        cache.put(body.getId(),body);
        return body;
    }

    /**
     * Retrieve user from cache
     * @param userId
     * @return
     */
    public User getCachedUser(String userId){
        User cachedUser = cache.getIfPresent(userId);
        if(Objects.isNull(cachedUser)){
            throw new UserNotFound(userId);
        }
        return cachedUser;
    }

}
