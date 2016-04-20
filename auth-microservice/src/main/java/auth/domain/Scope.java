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

    private String id;

    private String scope;

}
