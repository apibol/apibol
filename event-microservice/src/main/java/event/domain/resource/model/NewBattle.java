package event.domain.resource.model;

import event.domain.Battle;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Data @EqualsAndHashCode(callSuper = false)
public class NewBattle extends NewGame<Battle> {

    private String playerOne;

    private String playerTwo;

    @NotEmpty
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss.SSSZ")
    private LocalDateTime time;

    NewBattle(){}
    
    @Override
    public Battle toDomain() {
        return Battle.createBattleWithoutResult(this.playerOne,this.playerTwo,this.time);
    }

}
