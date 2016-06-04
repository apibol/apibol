package event.domain.specification;

import event.domain.Event;
import event.domain.Game;
import event.domain.Period;
import specification.AbstractSpecification;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Define if event is opened
 *
 * @author Claudio E. de Oliveira on 04/06/16.
 */
public class EventIsOpenByTimeRange extends AbstractSpecification<Event> {

    private final LocalDateTime requestTime;

    public EventIsOpenByTimeRange(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * Produces a new specification with request time is now
     * @return
     */
    public static EventIsOpenByTimeRange createWithNow() {
        return new EventIsOpenByTimeRange(LocalDateTime.now());
    }

    @Override
    public Boolean isSatisfiedBy(Event event) {
        checkNotNull(event,"Event cannot be null");
        return event.isOpen(this.requestTime);
    }

}
