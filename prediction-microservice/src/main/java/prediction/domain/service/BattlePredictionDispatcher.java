package prediction.domain.service;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import prediction.domain.BattlePrediction;
import prediction.domain.BattleResult;
import prediction.domain.PredictionEvaluator;
import prediction.domain.UserPoints;
import prediction.domain.repository.BattlePredictionRepository;

import java.util.List;

/**
 * Predictions Dispatcher
 *
 * @author Claudio E. de Oliveira on 22/03/16.
 */
@Log4j2
@Component
public class BattlePredictionDispatcher {

    private final BattlePredictionService battlePredictionService;

    private final SenderService senderService;

    @Autowired
    public BattlePredictionDispatcher(BattlePredictionService battlePredictionService, SenderService senderService) {
        this.battlePredictionService = battlePredictionService;
        this.senderService = senderService;
    }

    /**
     * Compute points by prediction
     *
     * @param battleResult - message from rabbitMQ
     */
    public void computePoints(final BattleResult battleResult) {
        log.info(String.format("[PROCESS-PREDICTIONS] Process results from game %s", battleResult.toString()));
        List<BattlePrediction> predictions = this.battlePredictionService.findByGameId(battleResult.getGameId());
        predictions.parallelStream().forEach(prediction -> {
            log.info(String.format("[EVALUATE-PREDICTION] Start Evaluate prediction id %s", prediction.getId()));
            Integer pointsEarned = new PredictionEvaluator(battleResult).evaluate(prediction);
            log.info(String.format("[EVALUATE-PREDICTION] End Evaluate prediction id %s", prediction.getId()));
            senderService.sendPoints(new UserPoints(battleResult.getGameId(), prediction.getOwner().getId(), pointsEarned, prediction.getOwner().getNickname(), prediction.getPredictor()));
        });
    }

}
