package auth.domain.repository;

import auth.domain.Credential;
import auth.domain.Scope;
import auth.infra.mapper.CredentialMapper;
import auth.infra.mapper.CredentialScopeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

/**
 * Credential Repository
 *
 * @author Claudio E. de Oliveira on on 19/04/16.
 */
@Repository
public class CredentialRepository {

    private static final String FIND_BY_NICKNAME = "SELECT * FROM credential where nickname = ?";

    private static final String INSERT_CREDENTIAL = "INSERT INTO credential(id,nickname,email,password) VALUES (?,?,?,?)";

    private static final String INSERT_ASSOCIATION = "INSERT INTO credentials_scope(id,nickname,scope) VALUES (?,?,?)";

    private static final String FIND_USER_SCOPE = "SELECT * FROM credentials_scope WHERE nickname = ?";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Credential findByNickname(String nickname) {
        final Credential credential = this.jdbcTemplate.queryForObject(FIND_BY_NICKNAME, new Object[]{nickname}, new CredentialMapper());
        List<Scope> scopes = this.jdbcTemplate.query(FIND_USER_SCOPE, new Object[]{nickname}, new CredentialScopeMapper());
        scopes.forEach(scope -> credential.addScope(scope.getScope()));
        return credential;
    }

    /**
     * Add a new Owner
     *
     * @param credential
     */
    public Credential addOwner(Credential credential){
        final Credential savedCredential = this.addCredential(credential, "owner");
        return savedCredential;
    }

    /**
     * Add a new Maintainer
     *
     * @param credential
     */
    public Credential addMaintainer(Credential credential){
        final Credential savedCredential = this.addCredential(credential, "maintainer");
        return savedCredential;
    }

    /**
     * Add a new User
     *
     * @param credential
     */
    public Credential addUser(Credential credential){
        final Credential savedCredential = this.addCredential(credential, "user");
        return savedCredential;
    }

    /**
     * Add credential in repository
     *
     * @param credential
     */
    private Credential addCredential(Credential credential,String scope) {
        String encodedPassword = this.passwordEncoder.encode(credential.getPassword());
        credential.assignNewId(newUuid()).assignEncodedPassword(encodedPassword).addScope(scope);
        this.jdbcTemplate.update(INSERT_CREDENTIAL,credential.getId(),credential.getNickname(),credential.getEmail(),encodedPassword);
        this.jdbcTemplate.update(INSERT_ASSOCIATION,newUuid(),credential.getNickname(),scope);
        return credential;
    }

    /**
     * Creates a new UUID
     *
     * @return
     */
    private String newUuid(){
        return UUID.randomUUID().toString();
    }

}
