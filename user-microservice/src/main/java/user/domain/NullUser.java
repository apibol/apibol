package user.domain;

import user.domain.AbstractNullObject;

/**
 * Represents a null or invalid user
 *  
 * @author Claudio E. de Oliveira on 13/03/16.
 */
public class NullUser extends User{
    
    @Override
    public boolean isNil() {
        return true;
    }
    
}
