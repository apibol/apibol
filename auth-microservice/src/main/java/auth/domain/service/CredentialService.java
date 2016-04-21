package auth.domain.service;

import auth.domain.Credential;
import auth.domain.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Credentials Service
 * @author Claudio E. de Oliveira on on 21/04/16.
 */
@Service
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
    public Credential createOwner(Credential credential){
        return this.createOwner(credential);
    }

    /**
     * Creates a new Maintainer
     *
     * @param credential
     * @return
     */
    public Credential createMaintainer(Credential credential){
        return this.createMaintainer(credential);
    }

    /**
     * Creates a new User
     *
     * @param credential
     * @return
     */
    public Credential createUser(Credential credential){
        return this.createUser(credential);
    }

}
