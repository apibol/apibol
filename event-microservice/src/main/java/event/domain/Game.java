package event.domain;

import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@EqualsAndHashCode(of = {"id"})
public abstract class Game {

    protected String id;

    protected LocalDateTime time;

    protected abstract GameResult result();

}
