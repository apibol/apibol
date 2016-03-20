package prediction.domain.exception;

import lombok.Getter;

/**
 * @author Claudio E. de Oliveira on 20/03/16.
 */
public class InvalidGame extends RuntimeException{
    
    @Getter
    private final String gameId;

    public InvalidGame(String gameId) {
        this.gameId = gameId;
    }
    
}
