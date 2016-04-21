package auth.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Scope for OAuth, e.g maintainer,user,owner
 *
 * @author Claudio E. de Oliveira on on 19/04/16.
 */
@Data
@EqualsAndHashCode(of = {"scope"})
public class Scope {

    private String scope;

    /**
     * Default constructor
     */
    Scope(){}

    /**
     * Constructor
     * @param scope
     */
    private Scope(String scope){
        this.scope = scope;
    }

    /**
     * Scope Factory
     * @param scope
     * @return
     */
    public static Scope create(String scope){
        return new Scope(scope);
    }

}
