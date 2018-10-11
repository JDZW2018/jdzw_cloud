package cn.com.myproject.oauth2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@Configuration
@EnableOAuth2Sso
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2SsoProperties oAuth2SsoProperties;

    public static final String URL_SECURITY_KEY = "url_security";

    public static final String METHOD_SECURITY_KEY = "method_security";
    /*@Autowired
    private Filter mySecurityContextPersistenceFilter;*/

    @Resource
    private Filter mySecurityFilter;

    @Autowired
    private MySsoLogoutHandler mySsoLogoutHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/private/**","/provide/**","/wau/**","/background/**","/oxr/**",
                "/nice/**","/oxy/**","/oxm/**","/oxc/**","/oxbl/**","/oxbc/**","/oxf/**",
                oAuth2SsoProperties.getLoginPath()).authenticated()


       // http.addFilterAfter(mySecurityContextPersistenceFilter,SecurityContextPersistenceFilter.class);
        .and().addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class);
        //FIXME 等有时间启用csrf
//          http
//            .csrf()
//            .csrfTokenRepository(csrfTokenRepository()).and()
//            .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
        http.csrf().disable();
        http.logout().logoutUrl("/logout").addLogoutHandler(mySsoLogoutHandler).invalidateHttpSession(true);
    }



    private Filter csrfHeaderFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                CsrfToken csrf = (CsrfToken) request
                        .getAttribute(CsrfToken.class.getName());
                if (csrf != null) {
                    Cookie cookie = new Cookie("XSRF-TOKEN",
                            csrf.getToken());
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                filterChain.doFilter(request, response);
            }
        };
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().  antMatchers("/*/assets/**","/*/third/**","/*/error","/*/css/**",
                "/*/img/**","/*/js/**");
    }

}
