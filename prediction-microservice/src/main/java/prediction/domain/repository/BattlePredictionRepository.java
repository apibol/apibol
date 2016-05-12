package prediction.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import prediction.domain.BattlePrediction;

import java.util.List;

/**
 * Prediction Repository
 *
 * @author Claudio E. de Oliveira on 28/02/16.
 */
public interface BattlePredictionRepository extends MongoRepository<BattlePrediction, String> {

    /**
     * Retrieves Prediction By GameId
     *
     * @param gameId
     * @return
     */
    List<BattlePrediction> findByGameId(String gameId);

    /**
     * Retrieves Prediction By Predictor
     *
     * @param predictor
     * @param ownerId
     * @return
     */
    List<BattlePrediction> findByPredictorAndOwnerId(String predictor, String ownerId);

    /**
     * Find predictions by predictor and gameId
     *
     * @param predictor
     * @param gameId
     * @param ownerId
     * @return
     */
    List<BattlePrediction> findByPredictorAndGameIdAndOwnerId(String predictor, String gameId, String ownerId);

}
