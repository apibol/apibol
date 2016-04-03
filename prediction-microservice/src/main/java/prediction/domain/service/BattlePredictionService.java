package prediction.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prediction.domain.BattlePrediction;
import prediction.domain.Game;
import prediction.domain.Predictor;
import prediction.domain.User;
import prediction.domain.repository.BattlePredictionRepository;
import prediction.domain.resource.model.BattlePredictionDTO;

import java.util.List;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Service
public class BattlePredictionService {

    private final BattlePredictionRepository battlePredictionRepository;

    private final PredictionService predictionService;
    
    private final GameService gameService;

    @Autowired
    public BattlePredictionService(BattlePredictionRepository battlePredictionRepository, PredictionService predictionService, GameService gameService) {
        this.battlePredictionRepository = battlePredictionRepository;
        this.predictionService = predictionService;
        this.gameService = gameService;
    }

    /**
     * Store prediction in repository
     *
     * @param battlePredictionDTO
     * @return
     */
    public BattlePrediction doPrediction(BattlePredictionDTO battlePredictionDTO) {
        User participantInfo = this.predictionService.getParticipantInfo(battlePredictionDTO.getPredictorId(), battlePredictionDTO.getUserId());
        Predictor predictor = this.predictionService.getPredictorInfo(battlePredictionDTO.getPredictorId());
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
     * @return
     */
    public List<BattlePrediction> findByPredictorId(String predictorId) {
        return this.battlePredictionRepository.findByPredictor(predictorId);
    }

    /**
     * Retrieves Prediction by Predictor Id and Game Id
     *
     * @param predictorId
     * @return
     */
    public List<BattlePrediction> findByPredictorIdAndGame(String predictorId,String gameId) {
        return this.battlePredictionRepository.findByPredictorAndGameId(predictorId,gameId);
    }

    /**
     * It removes prediction by Id
     * @param id
     */
    public void deletePredictionById(String id){
        this.battlePredictionRepository.delete(id);
    }

}
