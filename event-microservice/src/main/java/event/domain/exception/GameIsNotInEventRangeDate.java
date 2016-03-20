package event.domain.exception;

import event.domain.Game;
import lombok.Data;
import lombok.Getter;

/**
 * @author Claudio E. de Oliveira on 20/03/16.
 */
public class GameIsNotInEventRangeDate extends RuntimeException {
    
    @Getter
    private final Game game;

    public GameIsNotInEventRangeDate(Game game) {
        this.game = game;
    }
    
}
