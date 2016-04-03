package ranking.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Ranking not found Exception
 * @author Claudio E. de Oliveira on 20/03/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Ranking not found")
public class RankingNotFound extends RuntimeException {

    @Getter
    private final String predictor;

    public RankingNotFound(String predictor) {
        this.predictor = predictor;
    }

}
