package prediction.domain.service;

import domain.SystemUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import prediction.domain.BattlePrediction;
import prediction.domain.Game;
import prediction.domain.Predictor;
import prediction.domain.User;
import prediction.domain.exception.AnyPredictionsInPredictor;
import prediction.domain.exception.GamePredictionNotFound;
import prediction.domain.exception.UserIsNotInPredictor;
import prediction.domain.exception.UserIsNotPredictionOwner;
import prediction.domain.repository.BattlePredictionRepository;
import prediction.domain.resource.model.BattlePredictionDTO;
import prediction.domain.specification.IsPredictionOwner;

import java.util.List;

/**
 * Battle Prediction Service
 *
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Service
@Log4j2
public class BattlePredictionService {

    private final BattlePredictionRepository battlePredictionRepository;

    private final PredictionService predictionService;
    
    private final GameService gameService;

    private final SystemUserService systemUserService;

    @Autowired
    public BattlePredictionService(BattlePredictionRepository battlePredictionRepository, PredictionService predictionService,
                                   GameService gameService,SystemUserService systemUserService) {
        this.battlePredictionRepository = battlePredictionRepository;
        this.predictionService = predictionService;
        this.gameService = gameService;
        this.systemUserService = systemUserService;
    }

    /**
     * Store prediction in repository
     *
     * @param battlePredictionDTO
     * @param name
     * @return
     */
    public BattlePrediction doPrediction(BattlePredictionDTO battlePredictionDTO, String name) {
        SystemUser loggedUser = this.systemUserService.loggerUserInfo(name);
        User participantInfo = this.predictionService.getParticipantInfo(battlePredictionDTO.getPredictorId(), loggedUser.getId());
        Predictor predictor = this.predictionService.getPredictorInfo(battlePredictionDTO.getPredictorId());
        final boolean isParticipant = predictor.isParticipant(loggedUser);
        if(!isParticipant){
            log.error(String.format("User %s is not in predictor %s",loggedUser.getId(),predictor.getId()));
            throw new UserIsNotInPredictor(predictor.getId(),loggedUser.getId());
        }
        Game game = this.gameService.getGameInfo(predictor.getEventId(),battlePredictionDTO.getGameId());
        battlePredictionDTO.assignOwner(participantInfo);
        return this.battlePredictionRepository.save(battlePredictionDTO.toDomain());
    }

    /**
     * Retrieves Prediction by Game Id
     *
     * @param gameId
     * @return
     */
    public List<BattlePrediction> findByGameId(String gameId) {
        return this.battlePredictionRepository.findByGameId(gameId);
    }

    /**
     * Retrieves Prediction by Predictor Id
     *
     * @param predictorId
     * @param name
     * @return
     */
    public List<BattlePrediction> findByPredictorId(String predictorId, String name) {
        final SystemUser loggedUser = this.systemUserService.loggerUserInfo(name);
        final List<BattlePrediction> userPredictions = this.battlePredictionRepository.findByPredictorAndOwnerId(predictorId, loggedUser.getId());
        if(CollectionUtils.isEmpty(userPredictions)){
            log.error(String.format("Predictions not found userId: %s predictorId: %s",loggedUser.getId(),predictorId));
            throw new AnyPredictionsInPredictor(loggedUser.getId(),predictorId);
        }
        return userPredictions;
    }

    /**
     * Retrieves Prediction by Predictor Id and Game Id
     *
     * @param predictorId
     * @param name
     * @return
     */
    public List<BattlePrediction> findByPredictorIdAndGame(String predictorId, String gameId, String name) {
        final SystemUser loggedUser = this.systemUserService.loggerUserInfo(name);
        final List<BattlePrediction> predictions = this.battlePredictionRepository.findByPredictorAndGameIdAndOwnerId(predictorId, gameId, loggedUser.getId());
        if(CollectionUtils.isEmpty(predictions)){
            log.error(String.format("Game prediction not found userId: %s predictorId: %s gameId: %s",loggedUser.getId(),predictorId,gameId));
            throw new GamePredictionNotFound(loggedUser.getId(),predictorId,gameId);
        }
        return predictions;
    }

    /**
     * It removes prediction by Id
     *
     * @param id
     * @param name
     */
    public void deletePredictionById(String id, String name){
        final SystemUser loggedUser = this.systemUserService.loggerUserInfo(name);
        final BattlePrediction battlePrediction = this.battlePredictionRepository.findOne(id);
        if(new IsPredictionOwner(battlePrediction).isSatisfiedBy(loggedUser)){
            this.battlePredictionRepository.delete(id);
        }else{
            log.error(String.format("Game prediction %s cannot be deleted by userId: %s",id,loggedUser.getId()));
            throw new UserIsNotPredictionOwner(name,id);
        }
    }

}
