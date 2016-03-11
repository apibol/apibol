package event.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Data @EqualsAndHashCode(callSuper = false)
public class Battle extends Game {

    private String playerOne;

    private String playerTwo;
    
    private String resultPlayerOne;

    private String resultPlayerTwo;

    private Battle(String playerOne, String playerTwo, LocalDateTime time){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.time = time;
    }

    public static Battle createBattleWithoutResult(String playerOne, String playerTwo, LocalDateTime time) {
        return new Battle(playerOne, playerTwo, time);
    }

    @Override
    protected GameResult result() {
        return null;
    }

}
