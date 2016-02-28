package championship.domain.repository;

import championship.domain.Championship;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Claudio E. de Oliveira on 27/02/16.
 */
@Repository
public class ChampionshipRepository extends CouchDbRepositorySupport<Championship> {
    
    @Autowired
    public ChampionshipRepository(CouchDbConnector db) {
        super(Championship.class, db);
    }
    
}
