package cn.com.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {


    @Autowired
    private BaseOAuth2ProtectedResourceDetails baseOAuth2ProtectedResourceDetails;

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    @RequestMapping("/login")
    public String login(HttpServletResponse response, HttpServletRequest request) {


        ResourceOwnerPasswordAccessTokenProvider resourceOwnerPasswordAccessTokenProvider = new ResourceOwnerPasswordAccessTokenProvider();
        ResourceOwnerPasswordResourceDetails resourceOwnerPasswordResourceDetails =
                new ResourceOwnerPasswordResourceDetails();
        resourceOwnerPasswordResourceDetails.setUsername("ly");
        resourceOwnerPasswordResourceDetails.setPassword("ly");
        resourceOwnerPasswordResourceDetails.setClientId(baseOAuth2ProtectedResourceDetails.getClientId());
        resourceOwnerPasswordResourceDetails.setClientSecret(baseOAuth2ProtectedResourceDetails.getClientSecret());
        resourceOwnerPasswordResourceDetails.setAccessTokenUri(baseOAuth2ProtectedResourceDetails.getAccessTokenUri());
        resourceOwnerPasswordResourceDetails.setId(baseOAuth2ProtectedResourceDetails.getId());
        resourceOwnerPasswordResourceDetails.setAuthenticationScheme(baseOAuth2ProtectedResourceDetails.getAuthenticationScheme());
        resourceOwnerPasswordResourceDetails.setClientAuthenticationScheme(baseOAuth2ProtectedResourceDetails.getClientAuthenticationScheme());
        resourceOwnerPasswordResourceDetails.setGrantType(baseOAuth2ProtectedResourceDetails.getGrantType());
        resourceOwnerPasswordResourceDetails.setScope(baseOAuth2ProtectedResourceDetails.getScope());
        resourceOwnerPasswordResourceDetails.setTokenName(baseOAuth2ProtectedResourceDetails.getTokenName());

        OAuth2AccessToken token = resourceOwnerPasswordAccessTokenProvider.obtainAccessToken(resourceOwnerPasswordResourceDetails,new DefaultAccessTokenRequest());
      //  response.setHeader("Authorization","Bearer,"+token.getValue());
        oAuth2RestTemplate.getOAuth2ClientContext().setAccessToken(token);
        return "login,"+token.getValue();
    }

    @RequestMapping("/tologin")
    public String tologin(HttpServletResponse response, HttpServletRequest request) {
        return "tologin";
    }

    @RequestMapping("/tologin1")
    public String tologin1(HttpServletResponse response, HttpServletRequest request) {
        return "tologin1";
    }

    @RequestMapping("/tologin2")
    public String tologin2(HttpServletResponse response, HttpServletRequest request) {
        return "tologin2";
    }

}
