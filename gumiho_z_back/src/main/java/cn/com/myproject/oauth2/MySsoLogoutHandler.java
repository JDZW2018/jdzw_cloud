package cn.com.myproject.oauth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Qualifier("mySsoLogoutHandler")
public class MySsoLogoutHandler  implements LogoutHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySsoLogoutHandler.class);

    @Value("${security.oauth2.sso.logout}")
    private String logoutUrl;

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        LOGGER.debug("executing MySsoLogoutHandler.logout");


        Object details = null;
        if(authentication != null) {
            details = authentication.getDetails();
        }
        if (details !=null && details.getClass().isAssignableFrom(OAuth2AuthenticationDetails.class)) {

            String accessToken = ((OAuth2AuthenticationDetails) details).getTokenValue();
            String backServerName =  "http://" + httpServletRequest.getServerName()
                    + ":"
                    + httpServletRequest.getServerPort();
            LOGGER.debug("token: {}", accessToken);
            handleLogOutResponse(httpServletResponse,httpServletRequest);
            try {
                new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
                httpServletResponse.sendRedirect(logoutUrl+accessToken+"&backUrl="+backServerName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleLogOutResponse(HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return;
        }
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setValue(null);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }
}
