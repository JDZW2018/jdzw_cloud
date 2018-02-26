package cn.com.myproject.security;

import cn.com.myproject.UaaServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationServer.class);

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private DataSource dataSource;

    @Bean // 声明TokenStore实现
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
    @Bean // 声明 ClientDetails实现
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

//		@Bean
//		public JwtAccessTokenConverter jwtAccessTokenConverter() {
//			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//			KeyPair keyPair = new KeyStoreKeyFactory(
//					new ClassPathResource("keystore.jks"), "foobar".toCharArray())
//					.getKeyPair("test");
//
//			converter.setKeyPair(keyPair);
//			return converter;
//		}

    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {


        endpoints.tokenStore(tokenStore());
        endpoints.approvalStore(approvalStore());
        endpoints.authorizationCodeServices(authorizationCodeServices());
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.PUT,HttpMethod.POST);
        endpoints.setClientDetailsService(clientDetails());
        endpoints.userDetailsService(myUserDetailService);
        endpoints.authenticationManager(authenticationManager);
        endpoints.accessTokenConverter(new DefaultAccessTokenConverter());
        //endpoints.accessTokenConverter(jwtAccessTokenConverter());

        LOG.info("==========="+endpoints.getAccessTokenConverter());
        LOG.info("==========="+endpoints.getApprovalStore());
        LOG.info("==========="+endpoints.getAuthorizationCodeServices());
        LOG.info("==========="+endpoints.getTokenServices());
        LOG.info("==========="+endpoints.getTokenStore());
        LOG.info("==========="+endpoints.getTokenGranter());
        LOG.info("==========="+endpoints.getTokenEnhancer());


    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }
}