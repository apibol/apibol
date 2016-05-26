package event.domain.specification;

import event.domain.Event;
import event.domain.Game;
import specification.AbstractSpecification;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * If game is opened for predictions
 *
 * @author Claudio E. de Oliveira on on 26/05/16.
 */
public class GameIsOpenForPredictions extends AbstractSpecification<String> {

    private final Event event;

    public GameIsOpenForPredictions(Event event) {
        checkNotNull(event,"Event cannnot be null");
        this.event = event;
    }

    @Override
    public Boolean isSatisfiedBy(String gameId) {
        checkNotNull(gameId,"Game id cannot be null");
        return event.isOpenForPredictions(gameId);
    }

}
