package infra.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * Produces encoder password
 *
 * @author Claudio E. de Oliveira on on 10/04/16.
 */
@Configuration
public class OAuthProducers {

    @Autowired
    private DataSource dataSource;

    @Bean(name = "passwordEncoder")
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "tokenStore")
    public JdbcTokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    @Bean(name = "authorizationCodeServices")
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

}
