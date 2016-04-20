package auth.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Credential Repository
 *
 * @author Claudio E. de Oliveira on on 19/04/16.
 */
@Repository
public class CredentialRepository {

    @Autowired
    private DataSource dataSource;

}
