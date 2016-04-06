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

    private final BattlePredictionRepository battlePredictionRepository;

    private final SenderService senderService;

    @Autowired
    public BattlePredictionDispatcher(BattlePredictionRepository battlePredictionRepository, SenderService senderService) {
        this.battlePredictionRepository = battlePredictionRepository;
        this.senderService = senderService;
    }

    /**
     * Compute points by prediction
     *
     * @param battleResult - message from rabbitMQ
     */
    public void computePoints(final BattleResult battleResult) {
        log.info(String.format("[PROCESS-PREDICTIONS] Process results from game %s", battleResult.toString()));
        List<BattlePrediction> predictions = battlePredictionRepository.findByGameId(battleResult.getGameId());
        predictions.parallelStream().forEach(prediction -> {
            Integer pointsEarned = new PredictionEvaluator(battleResult).evaluate(prediction);
            senderService.sendPoints(new UserPoints(battleResult.getGameId(), prediction.getOwner().getId(), pointsEarned, prediction.getOwner().getNickname(), prediction.getPredictor()));
        });
    }

}
