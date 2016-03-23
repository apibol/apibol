package event.domain;

import event.domain.resource.model.BattleResultDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Data @EqualsAndHashCode(callSuper = false)
public class Battle extends Game<BattleResultDTO> {

    private String playerOne;

    private String playerTwo;
    
    private String resultPlayerOne;

    private String resultPlayerTwo;

    Battle(){}
    
    private Battle(String id,String playerOne, String playerTwo, LocalDateTime time){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.time = time;
        this.id = id;
    }

    public static Battle createBattleWithoutResult(String playerOne, String playerTwo, LocalDateTime time) {
        return new Battle(UUID.randomUUID().toString(),playerOne, playerTwo, time);
    }

    @Override
    public void updateGame(BattleResultDTO result) {
        this.resultPlayerOne = result.getPlayerOneResult();
        this.resultPlayerTwo = result.getPlayerTwoResult();
    }

}
