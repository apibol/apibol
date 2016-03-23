package domain.specification;

import org.junit.Test;
import prediction.domain.BattlePrediction;
import prediction.domain.BattleResult;
import prediction.domain.User;
import prediction.domain.specification.WinnerMatchPrediction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Claudio E. de Oliveira on 22/03/16.
 */
public class WinnerMatchPredictionTests {
    
    @Test
    public void testPlayerOneWinAndCorrect(){
        BattleResult battleResult = new BattleResult().playerOneResult("4").playerTwoResult("0").gameId("10");
        assertTrue(new WinnerMatchPrediction(battleResult).isSatisfiedBy(BattlePrediction.newBattlePrediction("10", "201", "2", "0", new User())));
    }

    @Test
    public void testPlayerTwoWinAndCorrect(){
        BattleResult battleResult = new BattleResult().playerOneResult("4").playerTwoResult("6").gameId("10");
        assertTrue(new WinnerMatchPrediction(battleResult).isSatisfiedBy(BattlePrediction.newBattlePrediction("10", "201", "2", "8", new User())));
    }

    @Test
    public void testGameTie(){
        BattleResult battleResult = new BattleResult().playerOneResult("4").playerTwoResult("4").gameId("10");
        assertFalse(new WinnerMatchPrediction(battleResult).isSatisfiedBy(BattlePrediction.newBattlePrediction("10", "201", "2", "8", new User())));
    }

    @Test
    public void testPlayerOneWinAndPredictionPlayerTwo(){
        BattleResult battleResult = new BattleResult().playerOneResult("2").playerTwoResult("1").gameId("10");
        assertFalse(new WinnerMatchPrediction(battleResult).isSatisfiedBy(BattlePrediction.newBattlePrediction("10", "201", "1", "2", new User())));
    }

}
