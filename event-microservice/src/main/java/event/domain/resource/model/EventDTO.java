package event.domain.resource.model;

import event.domain.Event;
import event.domain.Period;
import event.domain.User;
import lombok.Data;

import java.util.UUID;

/**
 * @author Claudio E. de Oliveira on 09/03/16.
 */
@Data
public class EventDTO {

    private String name;

    private Period period;

    private Boolean open = Boolean.FALSE;
    
    private String ownerId;

    public Event toDomain(User owner){
        return Event.newChampionship(UUID.randomUUID().toString(), this.name, this.period, this.open, owner);
    }

}
