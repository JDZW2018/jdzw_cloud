package cn.com.myproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.Filter;

/**
 * 此方法为url验证的主config
 * Created by liyang-macbook on 2017/6/17.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String URL_SECURITY_KEY = "url_security";

    public static final String METHOD_SECURITY_KEY = "method_security";


    @Autowired
    private AuthenticationProvider myAuthenticationProvider;

    @Resource
    private Filter mySecurityFilter;


//    @Resource
//    private Filter myAuthenticationFilter;
//    @Autowired
//    private RememberUserDetailService rememberUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //FIXME remember需要存储到redis中
       // TokenBasedRememberMeServices services = new TokenBasedRememberMeServices("myproject",rememberUserDetailService);

        http.authorizeRequests().anyRequest().authenticated();


        http.addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class)
  //              .addFilterAt(myAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .formLogin().loginPage("/tologin").successForwardUrl("/").failureForwardUrl("/tologin").loginProcessingUrl("/tologin")
//                .permitAll()
//                .and().rememberMe().rememberMeParameter("isRemember")
               // .rememberMeServices(services)
        ;

        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
        auth.eraseCredentials(false);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().  antMatchers("/assets/**","/third/**","/error",
                "/menu/get","/defaultKaptcha","/tologin","/css/**",
                "/img/**","/js/**","/UEditor/**","/favicon.ico","/info","/status","/hystrix.stream","/mappings");
    }

    @Bean(name="myAuthenticationManager")
    public AuthenticationManager get() throws Exception {
        return super.authenticationManagerBean();
    }


}
