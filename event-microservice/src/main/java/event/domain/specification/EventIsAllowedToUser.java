package event.domain.specification;

import event.domain.Event;
import specification.AbstractSpecification;

import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Define if event is opened for a specific user
 *
 * @author Claudio E. de Oliveira on 04/06/16.
 */
public class EventIsAllowedToUser extends AbstractSpecification<Event> {

    private final String userId;

    public EventIsAllowedToUser(String userId) {
        this.userId = userId;
    }

    @Override
    public Boolean isSatisfiedBy(Event event) {
        checkNotNull(event,"Event cannot be null");
        if (event.getOpen()) {
            return Boolean.TRUE;
        }
        return event.isParticipant(userId);
    }

}
