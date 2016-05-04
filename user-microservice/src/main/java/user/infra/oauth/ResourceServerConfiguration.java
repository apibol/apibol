package user.infra.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * User Resource server main configuration
 *
 * @author Claudio E. de Oliveira on on 28/04/16.
 */
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().anyRequest().authenticated().and().httpBasic().disable();
        http.authorizeRequests().antMatchers("**/user/**").permitAll();
    }

}
