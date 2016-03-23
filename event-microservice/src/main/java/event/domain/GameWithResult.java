package event.domain;

/**
 * @author Claudio E. de Oliveira on 22/03/16.
 */
public interface GameWithResult<T> {
    
    void updateGame(T result);
    
}
