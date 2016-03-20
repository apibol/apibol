package prediction.domain.resource.model;

import prediction.domain.Prediction;

/**
 * Convert to domain object
 *
 * @author Claudio E. de Oliveira on 20/03/16.
 */
public interface DomainConverter<T extends Prediction> {
    
    T toDomain();
    
}
