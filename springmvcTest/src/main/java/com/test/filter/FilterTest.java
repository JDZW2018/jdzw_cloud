package com.test.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Tianfusheng
 * @date 2018/9/8
 */
@Component
public class FilterTest implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("hello filter");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("filter end");
    }

    @Override
    public void destroy() {

    }
}
