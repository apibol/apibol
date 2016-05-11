package predictor.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import predictor.domain.Predictor;

import java.util.List;

/**
 * @author Claudio E. de Oliveira on 10/03/16.
 */
public interface PredictorRepository extends MongoRepository<Predictor,String> {

    /**
     * Find Predictor by event Id
     * @param eventId
     * @return
     */
    Predictor findByEventId(String eventId);

    /**
     * Find Predictor by participant and id
     * @param id
     * @param participantId
     * @return
     */
    Predictor findByIdAndParticipantsId(String id,String participantId);

    /**
     * Find user predictors
     *
     * @param userId
     * @return
     */
    List<Predictor> findByInvitationsUserId(String userId);

}
