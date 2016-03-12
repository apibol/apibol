package predictor.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import predictor.domain.Event;
import predictor.domain.Participant;
import predictor.domain.Predictor;
import predictor.domain.repository.PredictorRepository;
import predictor.domain.resource.PredictorDTO;

/**
 * @author Claudio E. de Oliveira on 10/03/16.
 */
@Service
public class PredictorService {
    
    @Autowired
    private PredictorRepository predictorRepository;

    @Autowired @LoadBalanced
    private RestTemplate restTemplate;

    @Value("${usermicroservice.user-info}")
    private String userInfoUrl;

    @Value("${eventmicroservice.event-info}")
    private String eventInfoUrl;

    
    public Predictor create(PredictorDTO predictorDTO){
        final ResponseEntity<Participant> userResponse = this.restTemplate.getForEntity(this.userInfoUrl + predictorDTO.getUserId(), Participant.class);
        final ResponseEntity<Event> eventResponse = this.restTemplate.getForEntity(this.eventInfoUrl + predictorDTO.getEventId(), Event.class);
        if(HttpStatus.OK.equals(userResponse.getStatusCode()) && HttpStatus.OK.equals(eventResponse.getStatusCode())){
            
            
        }
        
        userResponse.getBody();    

        return null;
    }
    
}
