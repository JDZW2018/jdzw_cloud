package cn.com.myproject;

import cn.com.myproject.security.MyResourceOwnerPasswordResourceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;

@SpringCloudApplication
public class AuthClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(AuthClient1Application.class, args);
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
//        AnnotationConfigEmbeddedWebApplicationContext configEmbeddedWebApplicationContext = (AnnotationConfigEmbeddedWebApplicationContext) applicationContext;
//
//        configEmbeddedWebApplicationContext.removeBeanDefinition("oauth2RemoteResource");
//
//        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(MyResourceOwnerPasswordResourceDetails.class).getBeanDefinition();
//
//        configEmbeddedWebApplicationContext.registerBeanDefinition("oauth2RemoteResource",beanDefinition);


        OAuth2RestTemplate template = this.applicationContext.getBean(UserInfoRestTemplateFactory.class).getUserInfoRestTemplate();
        ResourceOwnerPasswordAccessTokenProvider resourceOwnerPasswordAccessTokenProvider = new ResourceOwnerPasswordAccessTokenProvider();

        template.setAccessTokenProvider(resourceOwnerPasswordAccessTokenProvider);

        return template;
    }


}
