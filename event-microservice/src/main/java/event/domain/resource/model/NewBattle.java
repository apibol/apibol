package event.domain.resource.model;

import event.domain.Battle;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Data @EqualsAndHashCode(callSuper = false)
public class NewBattle extends NewGame<Battle> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @NotEmpty(message = "player one cannot be null")
    private String playerOne;

    @NotEmpty(message = "player two cannot be null")
    private String playerTwo;

    @NotEmpty(message = "time cannot be null")
    private String time;

    NewBattle(){}
    
    @Override
    public Battle toDomain() {
        return Battle.createBattleWithoutResult(this.playerOne,this.playerTwo,LocalDateTime.parse(this.time,FORMATTER));
    }

}
