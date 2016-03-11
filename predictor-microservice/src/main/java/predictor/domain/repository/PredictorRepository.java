package predictor.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import predictor.domain.Predictor;

/**
 * @author Claudio E. de Oliveira on 10/03/16.
 */
public interface PredictorRepository extends MongoRepository<Predictor,String> {
}
