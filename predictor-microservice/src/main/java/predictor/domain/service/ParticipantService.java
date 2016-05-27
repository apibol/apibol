package predictor.domain.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import predictor.domain.Participant;
import predictor.domain.exception.InvalidParticipant;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Claudio E. de Oliveira on 06/03/16.
 */
@Service
@RefreshScope
@Log4j2
public class ParticipantService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${services.user.info}")
    private String url;

    private final Cache<String,Participant> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(24L, TimeUnit.HOURS).build();

    @HystrixCommand(fallbackMethod = "getParticipantInCache")
    public Participant getUserInfo(String participantId){
        log.info("[REQUEST-PARTICIPANT-INFO] Request event info ");
        ResponseEntity<Participant> response = this.restTemplate.getForEntity(this.url + participantId, Participant.class);
        final Participant participant = response.getBody();
        cache.put(participant.getId(),participant);
        return participant;
    }

    /**
     * Retrieve participant from cache
     * @param participantId
     * @return
     */
    public Participant getParticipantInCache(String participantId){
        Participant cachedParticipant = cache.getIfPresent(participantId);
        if(Objects.isNull(cachedParticipant)){
            log.error(String.format("[REQUEST-PARTICIPANT-INFO] CACHE - Error on retrieve participant %s information", participantId));
            throw new InvalidParticipant(participantId);
        }
        return cachedParticipant;
    }
        
}
