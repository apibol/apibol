package auth.domain.service;

import auth.domain.Credential;
import auth.domain.CredentialData;
import auth.domain.exception.UserNotFoundByNickname;
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
    public CredentialData loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Credential credential = credentialRepository.findByNickname(username);
            return new CredentialData(credential);
        }catch (UserNotFoundByNickname userNotFoundByNickname){
            throw new UsernameNotFoundException("User not exists or invalid username");
        }
    }

}
