package user.domain.repository;

import com.couchbase.client.protocol.views.Query;
import org.springframework.data.repository.CrudRepository;
import user.domain.User;

import java.util.List;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
public interface UserRepository extends CrudRepository<User,String>{
    
    List<User> all(Query query);
    
}
