package predictor.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import predictor.domain.Participant;
import predictor.domain.exception.InvalidParticipant;

/**
 * @author Claudio E. de Oliveira on 06/03/16.
 */
@Service
public class ParticipantService {

    @Autowired @LoadBalanced
    private RestTemplate restTemplate;

    @Value("${usermicroservice.user-info}")
    private String url;
    
    public Participant getUserInfo(String userId){
        ResponseEntity<Participant> response = this.restTemplate.getForEntity(this.url + userId, Participant.class);
        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }else {
            throw new InvalidParticipant(userId);
        }
    }
        
}
