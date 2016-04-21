package auth.domain.resource.response;

import auth.domain.Credential;
import auth.domain.Scope;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Credential for Response Body
 *
 * @author Claudio E. de Oliveira on on 21/04/16.
 */
@Data
@EqualsAndHashCode(of = {"email"})
public class CredentialResponse {

    private String id;

    private String email;

    private String nickname;

    private Set<Scope> scopes = new HashSet<>();

    /**
     * Default constructor
     */
    CredentialResponse() {
    }

    /**
     * Constructor
     *
     * @param id
     * @param email
     * @param nickname
     * @param scopes
     */
    private CredentialResponse(String id, String email, String nickname, Set<Scope> scopes) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.scopes = scopes;
    }

    /**
     * Build new credential response
     *
     * @param credential
     * @return
     */
    public static CredentialResponse newResponse(final Credential credential) {
        return new CredentialResponse(credential.getId(), credential.getEmail(), credential.getNickname(), credential.getScopes());
    }

}
