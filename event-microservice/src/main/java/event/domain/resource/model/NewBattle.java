package event.domain.resource.model;

import event.domain.Battle;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Data @EqualsAndHashCode(callSuper = false)
public class NewBattle extends NewGame<Battle> {

    private String playerOne;

    private String playerTwo;

    private LocalDateTime time;

    @Override
    public Battle toDomain() {
        return Battle.createBattleWithoutResult(this.playerOne,this.playerTwo,this.time);
    }

}
