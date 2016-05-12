package prediction.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Game prediction not found
 *
 * @author Claudio E. de Oliveira on on 11/05/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Game prediction not found")
public class GamePredictionNotFound extends RuntimeException {

    @Getter
    private final String userId;

    @Getter
    private final String predictorId;

    @Getter
    private final String gameId;

    public GamePredictionNotFound(String userId, String predictorId,String gameId) {
        this.userId = userId;
        this.predictorId = predictorId;
        this.gameId = gameId;
    }

}
