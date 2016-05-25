package prediction.infra.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author Claudio E. de Oliveira on on 22/04/16.
 */
@Configuration
public class OAuthProducer {

    @Value("${config.oauth2.privateKey}")
    private String privateKey;

    @Value("${config.oauth2.publicKey}")
    private String publicKey;

    @Bean
    public JwtTokenStore tokenStore() throws Exception {
        JwtAccessTokenConverter enhancer = new JwtAccessTokenConverter();
        enhancer.setSigningKey(privateKey);
        enhancer.setVerifierKey(publicKey);
        enhancer.afterPropertiesSet();
        return new JwtTokenStore(enhancer);
    }

}
