package predictor.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import predictor.domain.Predictor;
import predictor.domain.repository.PredictorRepository;
import predictor.domain.resource.PredictorDTO;

/**
 * @author Claudio E. de Oliveira on 10/03/16.
 */
@Service
public class PredictorService {
    
    private final PredictorRepository predictorRepository;
    
    private final RestTemplate restTemplate;

    @Autowired
    public PredictorService(PredictorRepository predictorRepository, @LoadBalanced RestTemplate restTemplate) {
        this.predictorRepository = predictorRepository;
        this.restTemplate = restTemplate;
    }
    
    public Predictor create(PredictorDTO predictorDTO){
        
        return null;
    }
    
}
