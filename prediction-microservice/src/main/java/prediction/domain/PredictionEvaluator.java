package prediction.domain;

import prediction.domain.specification.FullMatchPrediction;
import prediction.domain.specification.GoalsInMatchPrediction;
import prediction.domain.specification.WinnerMatchPrediction;

/**
 * Evaluate the prediction
 *
 * @author Claudio E. de Oliveira on 22/03/16.
 */
public class PredictionEvaluator {

    private final FullMatchPrediction fullMatchPrediction;

    private final WinnerMatchPrediction winnerMatchPrediction;

    private final GoalsInMatchPrediction goalsInMatchPrediction;

    public PredictionEvaluator(BattleResult battleResult) {
        this.fullMatchPrediction = new FullMatchPrediction(battleResult);
        this.winnerMatchPrediction = new WinnerMatchPrediction(battleResult);
        this.goalsInMatchPrediction = new GoalsInMatchPrediction(battleResult);
    }

    /**
     * Evaluate the battle prediction
     *
     * @param battlePrediction
     * @return
     */
    public Integer evaluate(BattlePrediction battlePrediction) {
        int byFull = this.fullMatchPrediction.isSatisfiedBy(battlePrediction) ? 6 : 0;
        int byWinner = this.winnerMatchPrediction.isSatisfiedBy(battlePrediction) ? 3 : 0;
        int byGoals = this.goalsInMatchPrediction.isSatisfiedBy(battlePrediction) ? 1 : 0;
        final int result = byFull > byWinner ? byFull : byWinner;
        return result > byGoals ? result : byGoals;
    }

}
