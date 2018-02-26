package cn.com.myproject.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 *
 */
@Configuration
@EnableOAuth2Sso
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private MySecurityFilter mySecurityFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin().loginPage("/tologin1").permitAll()
                .and()
                .authorizeRequests().antMatchers("/private/**").authenticated().and()
        .addFilterAt(mySecurityFilter, FilterSecurityInterceptor.class);
    }


}
