package prediction.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Claudio E. de Oliveira on 20/03/16.
 */
@Data
public class Game {

    private String id;

    private LocalDateTime time;
    
}
