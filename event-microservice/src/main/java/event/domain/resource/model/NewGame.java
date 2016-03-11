package event.domain.resource.model;

import event.domain.Game;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
public abstract class NewGame<T extends Game> {
    
    public abstract T toDomain();

}
