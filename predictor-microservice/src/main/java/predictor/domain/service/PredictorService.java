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
import predictor.domain.exception.ParticipantNotInPredictor;
import predictor.domain.repository.PredictorRepository;
import predictor.domain.resource.PredictorDTO;

import java.util.List;
import java.util.Objects;

/**
 * @author Claudio E. de Oliveira on 10/03/16.
 */
@Service
public class PredictorService {

    private final ParticipantService participantService;
            
    private final EventService eventService;

    private final PredictorRepository predictorRepository;
    
    @Autowired
    public PredictorService(ParticipantService participantService, EventService eventService,PredictorRepository predictorRepository) {
        this.participantService = participantService;
        this.eventService = eventService;
        this.predictorRepository = predictorRepository;
    }

    /**
     * Create or updates a predictor
     * @param predictorDTO
     * @return
     */
    public Predictor create(PredictorDTO predictorDTO) {
        Participant newParticipant = this.participantService.getUserInfo(predictorDTO.getUserId());
        Event event = this.eventService.getEventInfo(predictorDTO.getEventId());
        Predictor predictor = this.predictorRepository.findByEventId(predictorDTO.getEventId());
        if(Objects.nonNull(predictor)){
            predictor.addParticipant(newParticipant);    
        }else{
            predictor = Predictor.createPredictor(event.getId(), newParticipant);
        }
        predictor = this.predictorRepository.save(predictor);
        return predictor;
    }

    /**
     * Retrieves all predictors 
     * 
     * @return all predictors
     */
    public List<Predictor> all(){
        return this.predictorRepository.findAll();
    }

    /**
     * Retrieves a predictor by Id
     * @param id
     * @return
     */
    public Predictor findOne(String id){
        return this.predictorRepository.findOne(id);
    }

    /**
     * Delete a predictor from repository
     * @param id
     */
    public void deletePredictor(String id){
        this.predictorRepository.delete(id);
    }

    /**
     * Get participant Info
     * @param predictorId
     * @param participantId
     * @return
     */
    public Participant findByPredictorAndParticipantId(String predictorId,String participantId){
        Predictor predictor = this.predictorRepository.findByIdAndParticipantsId(predictorId, participantId);
        if(Objects.isNull(predictor)){
            throw new ParticipantNotInPredictor(participantId,predictorId);
        }else {
            return predictor.participantInfo(participantId);
            
        }
    }
    
}
