package predictor.domain.service;

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
import predictor.domain.Event;
import predictor.domain.Participant;
import predictor.domain.exception.InvalidEvent;
import predictor.domain.exception.InvalidParticipant;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Claudio E. de Oliveira on 06/03/16.
 */
@Service
@RefreshScope
@Log4j2
public class EventService {

    @Autowired @LoadBalanced
    private RestTemplate restTemplate;

    @Value("${services.event.info}")
    private String eventInfoUrl;

    private final Cache<String,Event> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(24L, TimeUnit.HOURS).build();

    @HystrixCommand(fallbackMethod = "getEventInCache")
    public Event getEventInfo(String eventId){
        ResponseEntity<Event> response = this.restTemplate.getForEntity(this.eventInfoUrl + eventId, Event.class);
        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }else {
            log.error(String.format("Error on retrieve event %s information",eventId));
            throw new InvalidEvent(eventId);
        }
    }

    /**
     * Retrieve event from cache
     * @param eventId
     * @return
     */
    public Event getEventInCache(String eventId){
        Event event = cache.getIfPresent(eventId);
        if(Objects.isNull(event)){
            throw new InvalidEvent(eventId);
        }
        return event;
    }
        
}
