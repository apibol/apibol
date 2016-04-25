package predictor.domain.specification;

import predictor.domain.Predictor;
import specification.AbstractSpecification;

/**
 * Indicates if predictor is private
 *
 * @author Claudio E. de Oliveira on on 25/04/16.
 */
public class IsPrivatePredictor extends AbstractSpecification<Predictor>{

    @Override
    public Boolean isSatisfiedBy(Predictor instance) {
        return Boolean.FALSE.equals(instance.getOpen());
    }

}
