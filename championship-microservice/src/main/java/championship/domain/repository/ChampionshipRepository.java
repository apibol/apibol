package championship.domain.repository;

import championship.domain.Championship;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Claudio E. de Oliveira on 27/02/16.
 */
public interface ChampionshipRepository extends MongoRepository<Championship, String> {
}
