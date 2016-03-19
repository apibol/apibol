package predictor.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import predictor.domain.Event;
import predictor.domain.Participant;
import predictor.domain.exception.InvalidEvent;

/**
 * @author Claudio E. de Oliveira on 06/03/16.
 */
@Service
public class EventService {

    @Autowired @LoadBalanced
    private RestTemplate restTemplate;

    @Value("${eventmicroservice.event-info}")
    private String eventInfoUrl;
    
    public Event getEventInfo(String eventId){
        ResponseEntity<Event> response = this.restTemplate.getForEntity(this.eventInfoUrl + eventId, Event.class);
        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }else {
            throw new InvalidEvent(eventId);
        }
    }
        
}
