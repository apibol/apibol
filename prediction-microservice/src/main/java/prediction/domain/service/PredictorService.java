package prediction.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import prediction.domain.Predictor;
import prediction.exception.InvalidPredictor;

/**
 * @author Claudio E. de Oliveira on 19/03/16.
 */
@Service
public class PredictorService {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @Value("${services.predictor.info}")
    private String predictorInfoUrl;
    
    
    public Predictor getEventInfo(String eventId){
        ResponseEntity<Predictor> response = this.restTemplate.getForEntity(this.predictorInfoUrl + eventId, Predictor.class);
        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }else {
            throw new InvalidPredictor(eventId);
        }
    }
    
}
