package auth.domain.service;

import auth.domain.CredentialData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Custom Authentication Service
 *
 * @author Claudio E. de Oliveira on on 22/04/16.
 */
@Component
public class AuthenticationService implements AuthenticationProvider {

    @Autowired
    private CredentialUserDetails credentialUserDetails;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String nickname = authentication.getName();
        String password = (String) authentication.getCredentials();
        CredentialData credentialData = this.credentialUserDetails.loadUserByUsername(nickname);
        if (Objects.isNull(credentialData) || !credentialData.getNickname().equalsIgnoreCase(nickname)) {
            throw new BadCredentialsException("nickname not found or invalid.");
        }
        if (!password.equals(credentialData.getPassword())) {
            throw new BadCredentialsException("wrong password.");
        }
        return new UsernamePasswordAuthenticationToken(credentialData, password, credentialData.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
