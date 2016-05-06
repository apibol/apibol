package prediction.domain.exception;

import lombok.Getter;

/**
 * @author Claudio E. de Oliveira on 12/03/16.
 */
public class InvalidPredictor extends RuntimeException{

    @Getter
    private final String predictorId;

    public InvalidPredictor(String predictorId) {
        this.predictorId = predictorId;
    }

}
