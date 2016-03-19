package prediction.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prediction.domain.BattlePrediction;
import prediction.domain.repository.BattlePredictionRepository;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Service
public class BattlePredictionService {
    
    private final BattlePredictionRepository battlePredictionRepository;

    private final PredictorService predictorService;
    
    @Autowired
    public BattlePredictionService(BattlePredictionRepository battlePredictionRepository,PredictorService predictorService) {
        this.battlePredictionRepository = battlePredictionRepository;
        this.predictorService = predictorService;
    }
    
    public BattlePrediction make(BattlePrediction battlePrediction){
        this.battlePredictionRepository.save(battlePrediction);
        return battlePrediction;
    }
    
}
