package cn.com.myproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

@SpringCloudApplication
public class AuthClient3Application {

    public static void main(String[] args) {
        SpringApplication.run(AuthClient3Application.class, args);
    }

    @Autowired
    private AuthorizationCodeResourceDetails authorizationCodeResourceDetails;


    @Bean
    public OAuth2RestTemplate oauth2RestTemplate() {

        OAuth2RestTemplate restTemplate =
                new OAuth2RestTemplate(authorizationCodeResourceDetails, new DefaultOAuth2ClientContext());

        return restTemplate;
    }
}
