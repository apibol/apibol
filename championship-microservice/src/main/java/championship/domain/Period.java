package championship.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Data
public class Period {
    
    private LocalDateTime start;
    
    private LocalDateTime end;

}
