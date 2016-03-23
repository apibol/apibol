package prediction.domain;

import prediction.domain.specification.FullMatchPrediction;
import prediction.domain.specification.WinnerMatchPrediction;

/**
 * Evaluate the prediction
 * @author Claudio E. de Oliveira on 22/03/16.
 */
public class PredictionEvaluator {

    private final FullMatchPrediction fullMatchPrediction;
    
    private final WinnerMatchPrediction winnerMatchPrediction;
    
    public PredictionEvaluator(BattleResult battleResult){
        this.fullMatchPrediction = new FullMatchPrediction(battleResult);
        this.winnerMatchPrediction = new WinnerMatchPrediction(battleResult);
    }

    /**
     * Evaluate the battle prediction 
     * @param battlePrediction
     * @return
     */
    public Integer evaluate(BattlePrediction battlePrediction){
        int byFull = this.fullMatchPrediction.isSatisfiedBy(battlePrediction) ? 6 : 0;
        int byWinner = this.winnerMatchPrediction.isSatisfiedBy(battlePrediction) ? 3 : 0;
        return byFull > byWinner ? byFull : byWinner;
    }
    
}
