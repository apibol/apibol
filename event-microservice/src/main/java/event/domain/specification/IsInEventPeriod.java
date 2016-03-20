package event.domain.specification;

import event.domain.Event;
import event.domain.Game;
import event.domain.Period;
import event.domain.resource.model.NewGame;
import specification.AbstractSpecification;
import specification.Specification;

import java.time.Duration;
import java.util.Date;

/**
 * Define if game is inside the event period
 *
 * @author Claudio E. de Oliveira on 20/03/16.
 */
public class IsInEventPeriod extends AbstractSpecification<Game> {

    private final Event event;

    public IsInEventPeriod(Event event) {
        this.event = event;
    }

    @Override
    public Boolean isSatisfiedBy(Game game) {
        Period eventPeriod = event.getPeriod();
        return eventPeriod.getStart().isBefore(game.getTime()) && eventPeriod.getEnd().isAfter(game.getTime());
    }

}
