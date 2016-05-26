package event.domain.resource.model;

import event.domain.Event;
import event.domain.Period;
import event.domain.User;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Event DTO
 *
 * @author Claudio E. de Oliveira on 09/03/16.
 */
@Data
public class EventDTO {

    /**
     * Default time to block
     */
    private static final Integer DEFAULT_HOURS = 2;

    @NotEmpty(message = "name cannot be null")
    private String name;

    @NotEmpty(message = "period cannot be null")
    private Period period;

    private Integer hoursLimitToBlock = DEFAULT_HOURS;

    private Boolean open = Boolean.TRUE;

    private Set<String> participants = new HashSet<>();

    /**
     * Build domain event
     *
     * @param owner
     * @return
     */
    public Event toDomain(User owner) {
        return Event.newEvent(UUID.randomUUID().toString(), this.name, this.period, this.open, owner,hoursLimitToBlock);
    }

}
