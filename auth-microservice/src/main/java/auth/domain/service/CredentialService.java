package auth.domain.service;

import auth.domain.Credential;
import auth.domain.repository.CredentialRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Credentials Service
 *
 * @author Claudio E. de Oliveira on on 21/04/16.
 */
@Service
@Log4j2
public class CredentialService {

    private final CredentialRepository credentialRepository;

    @Autowired
    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    /**
     * Creates a new Owner
     *
     * @param credential
     * @return
     */
    public Credential createOwner(Credential credential) {
        log.info("Creating new [OWNER]");
        return this.credentialRepository.addOwner(credential);
    }

    /**
     * Creates a new Maintainer
     *
     * @param credential
     * @return
     */
    public Credential createMaintainer(Credential credential) {
        log.info("Creating new [MAINTAINER]");
        return this.credentialRepository.addMaintainer(credential);
    }

    /**
     * Creates a new User
     *
     * @param credential
     * @return
     */
    public Credential createUser(Credential credential) {
        log.info("Creating new [USER]");
        return this.credentialRepository.addUser(credential);
    }

}
