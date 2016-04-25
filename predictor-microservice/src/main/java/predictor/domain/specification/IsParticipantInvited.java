package predictor.domain.specification;

import predictor.domain.Predictor;
import specification.AbstractSpecification;

/**
 * Indicates if participant is invited
 *
 * @author Claudio E. de Oliveira on on 25/04/16.
 */
public class IsParticipantInvited extends AbstractSpecification<Predictor> {

    private final String participantId;

    public IsParticipantInvited(String participantId) {
        this.participantId = participantId;
    }

    @Override
    public Boolean isSatisfiedBy(Predictor instance) {
        return instance.getInvitations().stream().filter(invitation -> invitation.getUserId().equals(participantId)).findAny().isPresent();
    }

}
