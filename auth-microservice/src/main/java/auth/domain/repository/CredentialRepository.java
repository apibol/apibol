package auth.domain.repository;

import auth.domain.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Credential findByNickname(String nickname) {
        return this.jdbcTemplate.queryForObject(FIND_BY_NICKNAME, new Object[]{nickname}, Credential.class);
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
        credential.assignNewId(newUuid());
        this.jdbcTemplate.update(INSERT_CREDENTIAL,credential.getId(),credential.getNickname(),credential.getEmail(),encodedPassword);
        this.jdbcTemplate.update(INSERT_ASSOCIATION,newUuid(),credential.getNickname(),scope);
        credential.addScope(scope);
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
