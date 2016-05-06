package prediction.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Invalid owner exception
 *
 * @author Claudio E. de Oliveira on on 05/05/16.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Invalid owner")
public class UserIsNotPredictionOwner extends RuntimeException{

    @Getter
    private final String nickname;

    @Getter
    private final String predictionId;

    public UserIsNotPredictionOwner(String nickname, String predictionId) {
        this.nickname = nickname;
        this.predictionId = predictionId;
    }

}
