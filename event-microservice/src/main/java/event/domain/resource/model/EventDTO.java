package event.domain.resource.model;

import event.domain.Event;
import event.domain.Period;
import event.domain.User;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author Claudio E. de Oliveira on 09/03/16.
 */
@Data
public class EventDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private Period period;

    private Boolean open = Boolean.FALSE;

    @NotEmpty
    private String ownerId;

    public Event toDomain(User owner) {
        return Event.newEvent(UUID.randomUUID().toString(), this.name, this.period, this.open, owner);
    }

}
