package cn.com.myproject.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 此方法为扩展用户登录时，用户名密码验证filter，
 * 如增加验证码验证
 * Created by liyang-macbook on 2017/6/23.
 */
//@Component("myAuthenticationFilter")
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static Logger logger = LoggerFactory.getLogger(MyAuthenticationFilter.class);


    @Resource(name="myAuthenticationManager")
    private AuthenticationManager authenticationManager;

    @PostConstruct
    public void init(){
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if(logger.isDebugEnabled()){
            logger.debug("进入......");
        }
        String requestCaptcha = request.getParameter("vrify");
        String genCaptcha = (String)request.getSession().getAttribute("vrifyCode");

        logger.info("开始校验验证码，生成的验证码为："+genCaptcha+" ，输入的验证码为："+requestCaptcha);

        if( !genCaptcha.equals(requestCaptcha)){
            try {
                logger.info("验证码错误");
                response.sendRedirect(request.getContextPath()+"/tologin?error=3");
                return null;
            } catch (IOException e) {
                logger.error("",e);
                e.printStackTrace();
            }
        }
        Authentication authentication = null;
        try {
            authentication = super.attemptAuthentication(request, response);
        }catch (BadCredentialsException exception){
            try {
                logger.info("用户密码错误");
                response.sendRedirect(request.getContextPath()+"/tologin?error=1");
            } catch (IOException e) {
                logger.error("",e);
                e.printStackTrace();
            }
        }catch(InternalAuthenticationServiceException exception) {
            try {
                logger.info("访问服务错误");
                response.sendRedirect(request.getContextPath()+"/tologin?error=2");
            } catch (IOException e) {
                logger.error("",e);
                e.printStackTrace();
            }
        }
        return authentication;
    }
}
