package auth.application;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.security.oauth2.client.DefaultOAuth2RequestAuthenticator;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public class GoogleOAuth2Authenticator extends DefaultOAuth2RequestAuthenticator {

    @Override
    public void authenticate(OAuth2ProtectedResourceDetails resource,
                             OAuth2ClientContext clientContext, ClientHttpRequest request) {
        OAuth2AccessToken accessToken = clientContext.getAccessToken();
        String tokenType = OAuth2AccessToken.BEARER_TYPE;
        request.getHeaders().set("Authorization", String.format("%s %s", tokenType, accessToken.getValue()));
    }

}