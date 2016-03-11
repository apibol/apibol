package event.domain.repository;

import event.domain.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Claudio E. de Oliveira on 27/02/16.
 */
public interface EventRepository extends MongoRepository<Event, String> {
}
