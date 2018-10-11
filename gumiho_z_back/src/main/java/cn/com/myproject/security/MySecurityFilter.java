package cn.com.myproject.security;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * url filter，主要为自定义url过滤
 * Created by liyang-macbook on 2017/6/22.
 */
@Component("mySecurityFilter")
public class MySecurityFilter   extends AbstractSecurityInterceptor implements Filter {
    private static final String BLACKLIST = "blacklist";
    private static final String BLACKLISTURI = "/blacklist";

    private static final Logger logger = LoggerFactory.getLogger(MySecurityFilter.class);

    @Autowired
    private CustomInvocationSecurityMetadataSourceService mySecurityMetadataSource;

    @Resource
    private CustomAccessDecisionManager customAccessDecisionManager;

    @PostConstruct
    public void init(){
        super.setAccessDecisionManager(customAccessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation( request, response, chain );
        invoke(fi);
    }


    @Override
    public Class<? extends Object> getSecureObjectClass(){
        return FilterInvocation.class;
    }


    public void invoke( FilterInvocation filterInvocation ) throws IOException, ServletException{
        if(logger.isDebugEnabled()){
            logger.debug("filter..........................");
        }
        if (!StringUtils.contains(filterInvocation.getRequestUrl(),"/assets/")) {
            SecurityContext context = (SecurityContext) filterInvocation.getHttpRequest().getSession().getAttribute("SPRING_SECURITY_CONTEXT");
            if(null != context){
                Authentication authentication  = context.getAuthentication();
                String baseUrl = "http://" + filterInvocation.getHttpRequest().getServerName()
                        + ":"
                        + filterInvocation.getHttpRequest().getServerPort();
                String uri = filterInvocation.getRequestUrl();
                if (!BLACKLISTURI.equals(uri)) {
                    for (GrantedAuthority ga : authentication.getAuthorities()) {
                        if (BLACKLIST.trim().equals(ga.getAuthority().trim())) {
                                filterInvocation.getHttpResponse().sendRedirect(baseUrl + BLACKLISTURI);
                            return ;
                        }
                    }
                }
            }
        }
        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
        try{
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        }finally{
            super.afterInvocation(token, null);
        }
    }


    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource(){
        if(logger.isDebugEnabled()){
            logger.debug("obtainSecurityMetadataSource");
        }
        return this.mySecurityMetadataSource;
    }

    @Override
    public void destroy(){
        if(logger.isDebugEnabled()){
            logger.debug("filter===========================end");
        }
    }

    @Override
    public void init( FilterConfig filterconfig ) throws ServletException{

        if(logger.isDebugEnabled()){
            logger.debug("filter===========================");
        }
    }

}
