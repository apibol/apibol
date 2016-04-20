package auth.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Credential for OAuth
 *
 * @author Claudio E. de Oliveira on on 19/04/16.
 */
@Data
@EqualsAndHashCode(of = {"email"})
public class Credential {

    private String id;

    private String email;

    private String nickname;

    private String password;

    private Set<Scope> scopes = new HashSet<>();

}
