package prediction.domain.service;

import domain.Participant;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import prediction.domain.Predictor;
import prediction.domain.exception.InvalidPredictor;

import java.text.MessageFormat;

/**
 * @author Claudio E. de Oliveira on 19/03/16.
 */
@Service
@RefreshScope
@Log4j2
public class PredictionService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${services.predictor.info}")
    private String predictorInfoUrl;

    @Value("${services.predictor.participant-info}")
    private String participantInfoUrl;

    /**
     * Get predictor information
     * @param predictorId
     * @return
     */
    public Predictor getPredictorInfo(String predictorId){
        ResponseEntity<Predictor> response = this.restTemplate.getForEntity(this.predictorInfoUrl + predictorId, Predictor.class);
        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }else {
            log.error(String.format("[GET-PREDICTOR-INFO] Error on get predictor %s info",predictorId));
            throw new InvalidPredictor(predictorId);
        }
    }

    /**
     * Get Participant info in predictor 
     * @param predictorId
     * @param participantId
     * @return
     */
    public Participant getParticipantInfo(String predictorId, String participantId){
        String completeUrl = MessageFormat.format(participantInfoUrl, predictorId, participantId);
        ResponseEntity<Participant> response = this.restTemplate.getForEntity(completeUrl, Participant.class);
        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }else {
            log.error(String.format("[GET-PREDICTOR-INFO] Error on get predictor %s info",predictorId));
            throw new InvalidPredictor(predictorId);
        }
    }
    
}
