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

    private String playerOne;

    private String playerTwo;

    private String time;

    NewBattle(){}
    
    @Override
    public Battle toDomain() {
        return Battle.createBattleWithoutResult(this.playerOne,this.playerTwo,LocalDateTime.parse(this.time,FORMATTER));
    }

}
