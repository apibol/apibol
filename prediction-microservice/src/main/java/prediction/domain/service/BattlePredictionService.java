package prediction.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prediction.domain.BattlePrediction;
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

    private final PredictorService predictorService;

    @Autowired
    public BattlePredictionService(BattlePredictionRepository battlePredictionRepository, PredictorService predictorService) {
        this.battlePredictionRepository = battlePredictionRepository;
        this.predictorService = predictorService;
    }

    /**
     * Store prediction in repository
     *
     * @param battlePredictionDTO
     * @return
     */
    public BattlePrediction doPrediction(BattlePredictionDTO battlePredictionDTO) {
        User participantInfo = this.predictorService.getParticipantInfo(battlePredictionDTO.getPredictorId(), battlePredictionDTO.getUserId());
        Predictor predictor = this.predictorService.getPredictorInfo(battlePredictionDTO.getPredictorId());
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

}
