package auth.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

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

}
