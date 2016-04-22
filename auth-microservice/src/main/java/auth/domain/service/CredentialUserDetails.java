package auth.domain.service;

import auth.domain.Credential;
import auth.domain.CredentialData;
import auth.domain.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Custom User Details Service
 *
 * @author Claudio E. de Oliveira on on 22/04/16.
 */
@Component
public class CredentialUserDetails implements UserDetailsService {

    private final CredentialRepository credentialRepository;

    @Autowired
    public CredentialUserDetails(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    // TODO Handle user not found
    public CredentialData loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential credential = credentialRepository.findByNickname(username);
        return new CredentialData(credential);
    }

}
