package predictor.domain.specification;

import predictor.domain.JoinPredictorRequest;
import predictor.domain.Predictor;
import specification.AbstractSpecification;

/**
 * Indicates if is valid predictor request
 *
 * @author Claudio E. de Oliveira on on 25/04/16.
 */
public class IsValidJoinPredictionRequest extends AbstractSpecification<JoinPredictorRequest> {

    private final Predictor predictor;

    public IsValidJoinPredictionRequest(Predictor predictor) {
        this.predictor = predictor;
    }

    @Override
    public Boolean isSatisfiedBy(JoinPredictorRequest instance) {
        return this.predictor.getInvitations().stream().filter(invitation -> invitation.getUserId().equals(instance.getParticipantId()) && invitation.getKey().equals(instance.getHash())).findAny().isPresent();
    }

}
