package auth.infra.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * OAuth configurations
 *
 * @author Claudio E. de Oliveira on on 19/04/16.
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager).approvalStoreDisabled().tokenStore(this.tokenStore).accessTokenConverter(this.jwtAccessTokenConverter);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
                .dataSource(this.dataSource)
                .usersByUsernameQuery("select nickname,password,true from users where nickname=?")
                .authoritiesByUsernameQuery("select nickname, scope from credentials_scope where nickname=?")
            .passwordEncoder(this.passwordEncoder);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        clients
            .inMemory()
                .withClient("user-service")
                    .secret("4f7ec648a48b9d3fa239b497f7b6b4d8019697bd")
                    .authorizedGrantTypes("password", "authorization_code","refresh_token", "implicit")
                    .authorities("maintainer", "owner","user")
                    .scopes("read", "write", "trust")
                    .accessTokenValiditySeconds(1800)
            .and()
                .withClient("event-service")
                .secret("f7641c289245d5d43d0aa96d0c14dbc6afba31fc")
                .authorizedGrantTypes("password", "authorization_code","refresh_token", "implicit")
                .authorities("maintainer", "owner","user")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(1800)
            .and()
                .withClient("predictor-service")
                .secret("d658a112c1160fdcb5c6a4c3ce33c716cdb84160")
                .authorizedGrantTypes("password", "authorization_code","refresh_token", "implicit")
                .authorities("maintainer", "owner","user")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(1800)
            .and()
                .withClient("prediction-service")
                .secret("da29f4b2ac608bdc8024b7ab084981fdf4014c16")
                .authorizedGrantTypes("password", "authorization_code","refresh_token", "implicit")
                .authorities("maintainer", "owner","user")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(1800)
            .and()
                .withClient("ranking-service")
                .secret("e186b815cfc1a9988030a21ad8f32d8a6559f3b8")
                .authorizedGrantTypes("password", "authorization_code","refresh_token", "implicit")
                .authorities("maintainer", "owner","user")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(1800);
    }

}
