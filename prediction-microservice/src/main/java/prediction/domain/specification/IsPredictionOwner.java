package prediction.domain.specification;

import domain.SystemUser;
import prediction.domain.Prediction;
import specification.AbstractSpecification;

/**
 * If User is prediction owner
 *
 * @author Claudio E. de Oliveira on on 05/05/16.
 */
public class IsPredictionOwner extends AbstractSpecification<SystemUser>{

    private final Prediction prediction;

    public IsPredictionOwner(Prediction prediction) {
        this.prediction = prediction;
    }

    @Override
    public Boolean isSatisfiedBy(SystemUser systemUser) {
        return this.prediction.getOwner().getNickname().equals(systemUser.getNickname());
    }

}
