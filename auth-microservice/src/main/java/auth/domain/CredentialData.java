package auth.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Credential for Security tasks Custom UserDetails Service
 *
 * @author Claudio E. de Oliveira on on 19/04/16.
 */
@Data
@EqualsAndHashCode(of = {"email"})
public class CredentialData implements UserDetails{

    private String id;

    private String email;

    private String nickname;

    private String password;

    private Set<Scope> scopes = new HashSet<>();

    CredentialData(){}

    public CredentialData(Credential base){
        this.id = base.getId();
        this.email = base.getEmail();
        this.nickname = base.getNickname();
        this.password = base.getPassword();
        this.scopes = base.getScopes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.scopes.stream().map(scope -> new SimpleGrantedAuthority(scope.getScope())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
