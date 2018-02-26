package cn.com.myproject.adminuser.controller;

import cn.com.myproject.redis.IRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 */
@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private AuthenticationManager myAuthenticationManager;

    @Autowired
    private IRedisService redisService;

    @RequestMapping("/tologin")
    public String toLogin(Integer error,HttpServletRequest request){
        if(error!=null){
            request.setAttribute("error",error);
        }
        return "login";
    }

    @RequestMapping("/third/loginname")
    public String login(String loginName,String password,Integer isRemember,HttpServletRequest request, HttpServletResponse response){

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginName, password);

		try {

            Authentication authentication = myAuthenticationManager.authenticate(authRequest); //调用loadUserByUsername
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession();

            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆

            response.sendRedirect("/index.html");

        } catch (AuthenticationException ex) {
            return "/login?error";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
