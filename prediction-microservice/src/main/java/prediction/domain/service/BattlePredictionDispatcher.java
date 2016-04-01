package prediction.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import prediction.domain.BattlePrediction;
import prediction.domain.BattleResult;
import prediction.domain.PredictionEvaluator;
import prediction.domain.UserPoints;
import prediction.domain.repository.BattlePredictionRepository;

import java.util.List;

/**
 * @author Claudio E. de Oliveira on 22/03/16.
 */
public class BattlePredictionDispatcher {
    
    private final BattlePredictionRepository battlePredictionRepository;

    @Autowired
    public BattlePredictionDispatcher(BattlePredictionRepository battlePredictionRepository) {
        this.battlePredictionRepository = battlePredictionRepository;
    }

    /**
     * Compute points by prediction
     * @param battleResult
     */
    public void computePoints(final BattleResult battleResult){
        List<BattlePrediction> predictions = battlePredictionRepository.findByGameId(battleResult.getGameId());
        predictions.parallelStream().forEach(prediction ->{
            Integer pointsEarned = new PredictionEvaluator(battleResult).evaluate(prediction);
            new UserPoints(battleResult.getGameId(),prediction.getOwner().getId(),pointsEarned);
        });
    }

}
