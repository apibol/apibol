package prediction.domain.specification;

import prediction.domain.BattlePrediction;
import prediction.domain.BattleResult;
import specification.AbstractSpecification;

/**
 * @author Claudio E. de Oliveira on 22/03/16.
 */
public class WinnerMatchPrediction extends AbstractSpecification<BattlePrediction> {
    
    private final BattleResult battleResult;

    public WinnerMatchPrediction(BattleResult battleResult) {
        this.battleResult = battleResult;
    }

    @Override
    public Boolean isSatisfiedBy(BattlePrediction instance) {
        Integer playerOneResult = Integer.valueOf(battleResult.playerOneResult());
        Integer playerTwoResult = Integer.valueOf(battleResult.playerTwoResult());
        Integer myPlayerOneResult = Integer.valueOf(instance.getPlayerOneResult());
        Integer myPlayerTwoResult = Integer.valueOf(instance.getPlayerTwoResult());
        switch (playerOneResult.compareTo(playerTwoResult)){
            case 0:
                return false;
            case 1:
                return myPlayerOneResult.compareTo(myPlayerTwoResult) > 0; 
            case -1:
                return myPlayerTwoResult.compareTo(myPlayerOneResult)> 0;
            default:
                return false;
        }
    }
    
}
