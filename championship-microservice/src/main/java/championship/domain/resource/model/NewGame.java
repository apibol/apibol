package championship.domain.resource.model;

import championship.domain.Game;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
public abstract class NewGame<T extends Game> {
    
    public abstract T toDomain();

}
