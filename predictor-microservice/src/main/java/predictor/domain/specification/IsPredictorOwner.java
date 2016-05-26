package predictor.domain.specification;

import domain.SystemUser;
import predictor.domain.Participant;
import predictor.domain.Predictor;
import specification.AbstractSpecification;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Indicates if participant is predictor owner
 *
 * @author Claudio E. de Oliveira on on 26/05/16.
 */
public class IsPredictorOwner extends AbstractSpecification<SystemUser> {

    private final Predictor predictor;

    public IsPredictorOwner(Predictor predictor) {
        checkNotNull(predictor,"Predictor cannot be null");
        this.predictor = predictor;
    }

    @Override
    public Boolean isSatisfiedBy(SystemUser loggedUser) {
        checkNotNull(loggedUser,"Logged user cannot be null");
        return this.predictor.getOwner().getId().equals(loggedUser.getId());
    }

}
