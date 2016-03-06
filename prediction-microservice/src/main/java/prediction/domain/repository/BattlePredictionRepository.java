package prediction.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import prediction.domain.BattlePrediction;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
public interface BattlePredictionRepository extends MongoRepository<BattlePrediction,String>{
}
