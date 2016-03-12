package predictor.domain.exception;

import lombok.Getter;

/**
 * @author Claudio E. de Oliveira on 12/03/16.
 */
public class InvalidEvent extends RuntimeException{
    
    @Getter
    private final String eventId;

    public InvalidEvent(String eventId) {
        this.eventId = eventId;
    }

}
