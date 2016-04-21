package auth.domain.resource.vo;

import auth.domain.Scope;
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
public class CredentialVO {

    private String email;

    private String nickname;

    private String password;

}
