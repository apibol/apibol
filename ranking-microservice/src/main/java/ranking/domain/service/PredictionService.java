package ranking.domain.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import domain.Participant;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ranking.domain.exception.UserNotInPredictor;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Claudio E. de Oliveira on 19/03/16.
 */
@Service
@RefreshScope
@Log4j2
public class PredictionService {

    private static final String KEY_PATTERN = "%s:%s";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${services.predictor.participant-info}")
    private String participantInfoUrl;

    private final Cache<String,Participant> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(1L, TimeUnit.HOURS).build();

    /**
     * Get Participant info in predictor
     *
     * @param predictorId
     * @param participantId
     * @return
     */
    @HystrixCommand(fallbackMethod = "getParticipantInCache")
    public Participant getParticipantInfo(String predictorId, String participantId){
        String completeUrl = MessageFormat.format(participantInfoUrl, predictorId, participantId);
        try{
            ResponseEntity<Participant> response = this.restTemplate.getForEntity(completeUrl, Participant.class);
            final Participant participant = response.getBody();
            final String key = String.format(KEY_PATTERN,predictorId,participantId);
            cache.put(key,participant);
            return participant;
        }catch (RestClientException notFound){
            log.error("[GET-PREDICTOR-INFO] Rest Client Exception",notFound);
            throw new UserNotInPredictor(predictorId,participantId);
        }
    }

    /**
     * Retrieve participant from cache
     * @param participantId
     * @return
     */
    public Participant getParticipantInCache(String predictorId,String participantId){
        final String key = String.format(KEY_PATTERN,predictorId,participantId);
        Participant cachedParticipant = cache.getIfPresent(key);
        if(Objects.isNull(cachedParticipant)){
            log.error(String.format("[GET-PREDICTOR-INFO] Error on get predictor %s info",predictorId));
            throw new UserNotInPredictor(predictorId,participantId);
        }
        return cachedParticipant;
    }
    
}
