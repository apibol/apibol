package event.domain.specification;

import event.domain.resource.model.EventDTO;
import specification.AbstractSpecification;

/**
 * Indicates if event is private
 *
 * @author Claudio E. de Oliveira on on 25/04/16.
 */
public class IsPrivateEvent extends AbstractSpecification<EventDTO>{

    @Override
    public Boolean isSatisfiedBy(EventDTO instance) {
        return Boolean.FALSE.equals(instance.getOpen());
    }

}
