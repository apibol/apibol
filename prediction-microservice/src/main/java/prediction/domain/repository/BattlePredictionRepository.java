package prediction.domain.repository;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import prediction.domain.BattlePrediction;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Repository
public class BattlePredictionRepository extends CouchDbRepositorySupport<BattlePrediction>{
    
    @Autowired
    public BattlePredictionRepository(CouchDbConnector db) {
        super(BattlePrediction.class, db);
    }
    
}
