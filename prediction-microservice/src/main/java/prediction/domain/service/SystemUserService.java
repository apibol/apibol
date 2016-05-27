package prediction.domain.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import domain.SystemUser;
import exception.UserNotFound;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Logged user service
 *
 * @author Claudio E. de Oliveira on on 28/04/16.
 */
@Service
@Log4j2
public class SystemUserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${services.user.info}")
    private String baseUrl;

    private static final String queryInfo = "user?nickname=";

    private final Cache<String,SystemUser> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(24L, TimeUnit.HOURS).build();

    /**
     * Retrieve logged user info
     *
     * @param nickname
     * @return
     */
    @HystrixCommand(fallbackMethod = "getUserInCache")
    public SystemUser loggerUserInfo(String nickname) {
        ResponseEntity<SystemUser> response = this.restTemplate.getForEntity(this.baseUrl + queryInfo + nickname, SystemUser.class);
        SystemUser body = response.getBody();
        cache.put(body.getNickname(),body);
        return body;
    }

    /**
     * Retrieve system user from cache
     *
     * @param nickname
     * @return
     */
    public SystemUser getUserInCache(String nickname){
        SystemUser cachedUser = cache.getIfPresent(nickname);
        if(Objects.isNull(cachedUser)){
            log.info(String.format("[GET-LOGGED-USER] User %s not found ", nickname));
            throw new UserNotFound(nickname);
        }
        return cachedUser;
    }

}
