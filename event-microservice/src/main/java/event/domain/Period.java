package event.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Data
public class Period {
    
    @NotEmpty
    private LocalDateTime start;

    @NotEmpty
    private LocalDateTime end;

}
