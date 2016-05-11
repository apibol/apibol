package predictor.domain.service;

import domain.SystemUser;
import lombok.extern.log4j.Log4j2;
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
import predictor.domain.exception.InvalidPredictor;
import predictor.domain.exception.ParticipantNotInPredictor;
import predictor.domain.repository.PredictorRepository;
import predictor.domain.resource.JoinPredictorDTO;
import predictor.domain.resource.PredictorDTO;

import java.util.List;
import java.util.Objects;

/**
 * @author Claudio E. de Oliveira on 10/03/16.
 */
@Service
@Log4j2
public class PredictorService {

    private final ParticipantService participantService;

    private final EventService eventService;

    private final PredictorRepository predictorRepository;

    private final SystemUserService systemUserService;

    @Autowired
    public PredictorService(ParticipantService participantService, EventService eventService, PredictorRepository predictorRepository,SystemUserService systemUserService) {
        this.participantService = participantService;
        this.eventService = eventService;
        this.predictorRepository = predictorRepository;
        this.systemUserService = systemUserService;
    }

    /**
     * Creates a predictor
     *
     * @param predictorDTO
     * @return
     */
    public Predictor create(PredictorDTO predictorDTO) {
        log.info("[CREATE-PREDICTOR] Creating predictor ");
        Participant newParticipant = this.participantService.getUserInfo(predictorDTO.getUserId());
        Event event = this.eventService.getEventInfo(predictorDTO.getEventId());
        Predictor predictor = Predictor.createPredictor(event.getId(), newParticipant,event.getOpen());
        predictor.join(newParticipant);
        predictor = this.predictorRepository.save(predictor);
        log.info("[CREATE-PREDICTOR] Predictor created with success ");
        return predictor;
    }

    /**
     * Add participant in Predictor
     *
     * @param predictorId
     * @param hash
     * @param joinPredictorDTO
     * @return
     */
    public Predictor join(String predictorId,String hash, JoinPredictorDTO joinPredictorDTO) {
        log.info(String.format("[ADD-PARTICIPANT] Adding participant %s in predictor %s ", joinPredictorDTO.getUserId(), predictorId));
        Participant newParticipant = this.participantService.getUserInfo(joinPredictorDTO.getUserId());
        Predictor predictor = this.predictorRepository.findOne(predictorId);
        if (Objects.nonNull(predictor)) {
            predictor.join(newParticipant);
            predictor = this.predictorRepository.save(predictor);
            log.info(String.format("[ADD-PARTICIPANT] Participant %s added in predictor %s ", joinPredictorDTO.getUserId(), predictorId));
            return predictor;
        } else {
            log.error("[ADD-PARTICIPANT] Invalid predictor or not found");
            throw new InvalidPredictor(predictorId);
        }
    }

    /**
     * Retrieves all predictors
     *
     * @return all predictors
     */
    public List<Predictor> all() {
        return this.predictorRepository.findAll();
    }

    /**
     * Retrieves a predictor by Id
     *
     * @param id
     * @return
     */
    public Predictor findOne(String id) {
        return this.predictorRepository.findOne(id);
    }

    /**
     * Delete a predictor from repository
     *
     * @param id
     * @param name
     */
    public void deletePredictor(String id, String name) {
        log.info(String.format("[DELETE-PREDICTOR] Delete predictor by id. Predictor %s", id));
        this.predictorRepository.delete(id);
    }

    /**
     * Get participant Info
     *
     * @param predictorId
     * @param participantId
     * @return
     */
    public Participant findByPredictorAndParticipantId(String predictorId, String participantId) {
        Predictor predictor = this.predictorRepository.findByIdAndParticipantsId(predictorId, participantId);
        if (Objects.isNull(predictor)) {
            throw new ParticipantNotInPredictor(participantId, predictorId);
        } else {
            return predictor.participantInfo(participantId);
        }
    }

    /**
     * Create a predictor by event
     *
     * @param predictor
     * @return
     */
    public Predictor saveByEvent(Predictor predictor){
        return this.predictorRepository.save(predictor);
    }

    /**
     * Find user predictors
     *
     * @param userId
     * @return
     */
    public List<Predictor> myPredictors(String userId){
        log.info(String.format("[MY-PREDICTORS] Retrieve my predictors user %s", userId));
        final SystemUser loggerUserInfo = this.systemUserService.loggerUserInfo(userId);
        return this.predictorRepository.findByInvitationsUserId(loggerUserInfo.getId());
    }

}
