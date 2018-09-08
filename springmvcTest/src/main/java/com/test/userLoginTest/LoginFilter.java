package com.test.userLoginTest;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Tianfusheng
 * @date 2018/9/8
 */
@Component
public class LoginFilter implements Filter {
    private static final String[] paths={"/User/login"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("loginFilter doFilter getIn");
        servletResponse.setContentType("text/html;charset=utf-8");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI().toString();
        PathMatcher matcher = new AntPathMatcher();
        //否为过滤，是为不过滤
        boolean flag = false;
        for(String path:paths){
            if(matcher.match(path,requestURI)){
                flag =true;
                break;
            }
        }
        if(!flag){
            String username = (String) request.getSession().getAttribute("username");
            if(StringUtils.isEmpty(username)){
                servletResponse.getWriter().print("尚未登录");
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("loginFilter doFilter end1");
    }

    @Override
    public void destroy() {

    }
}
