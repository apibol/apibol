package auth.application;

import org.springframework.cloud.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

public class CustomOAuth2RestTemplate implements UserInfoRestTemplateCustomizer {
 
    @Override
    public void customize(OAuth2RestTemplate template) {
        template.setAuthenticator(new GoogleOAuth2Authenticator());
    }

}