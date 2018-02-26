package cn.com.myproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

       // http.csrf().disable();

        http.formLogin().loginPage("/login").permitAll().and()
                .logout().permitAll()
                .and()
                .requestMatchers().antMatchers("/login**", "/oauth/**","/")
                .and().authorizeRequests().antMatchers("/oauth1/**").authenticated();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {

    }

}