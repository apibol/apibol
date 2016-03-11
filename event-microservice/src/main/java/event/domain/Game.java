package event.domain;

import java.time.LocalDateTime;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
public abstract class Game {

    protected String id;

    protected LocalDateTime time;

    protected abstract GameResult result();

}
