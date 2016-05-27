package predictor.domain.service;

import domain.SystemUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import predictor.domain.Event;
import predictor.domain.JoinPredictorRequest;
import predictor.domain.Participant;
import predictor.domain.Predictor;
import predictor.domain.exception.InvalidPredictor;
import predictor.domain.exception.ParticipantIsNotOwnerException;
import predictor.domain.exception.ParticipantNotInPredictor;
import predictor.domain.exception.PredictorNotFound;
import predictor.domain.repository.PredictorRepository;
import predictor.domain.resource.JoinPredictorDTO;
import predictor.domain.resource.PredictorDTO;
import predictor.domain.specification.IsPredictorOwner;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
        Predictor predictor = Predictor.createPredictor(UUID.randomUUID().toString(),event.getId(), newParticipant,event.getOpen());
        predictor.join(JoinPredictorRequest.create(newParticipant,""));
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
        final SystemUser loggerUserInfo = this.systemUserService.loggerUserInfo(joinPredictorDTO.getUserId());
        Participant newParticipant = this.participantService.getUserInfo(loggerUserInfo.getId());
        Predictor predictor = this.predictorRepository.findOne(predictorId);
        if (Objects.nonNull(predictor)) {
            predictor.join(JoinPredictorRequest.create(newParticipant,hash));
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
        final Predictor predictor = this.predictorRepository.findOne(id);
        if (Objects.isNull(predictor)){
            log.info(String.format("[GET-PREDICTOR-ID] Predictor %s not found in database", id));
            throw new PredictorNotFound(id);
        }
        return predictor;
    }

    /**
     * Delete a predictor from repository
     *
     * @param id
     * @param name
     */
    public void deletePredictor(String id, String name) {
        final SystemUser systemUser = this.systemUserService.loggerUserInfo(name);
        final Predictor predictor = this.predictorRepository.findOne(id);
        if(new IsPredictorOwner(predictor).isSatisfiedBy(systemUser)){
            log.info(String.format("[DELETE-PREDICTOR] Delete predictor by id. Predictor %s by user %s", id,name));
            this.predictorRepository.delete(id);
        }else{
            log.error(String.format("[DELETE-PREDICTOR] User %s is not owner on predictor %s ", name,id));
            throw new ParticipantIsNotOwnerException(id,systemUser.getId());
        }
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
            log.error(String.format("[GET-PARTICIPANT-IN-PREDICTOR] Participant %s is not in predictor %s ", participantId,predictorId));
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
        log.info("[SAVE-NEW-PREDICTOR-BY-EVENT] Receive new event "+ predictor.getId());
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
