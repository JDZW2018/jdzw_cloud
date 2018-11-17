package cn.com.myproject.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liyang-macbook on 2016/11/21.
 */
public class AccessFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
    private static final String LOGIN_NAME = "login_name";
    @Override
    public String filterType() {
        return "pre";
    }
    @Override
    public int filterOrder() {
        return 0;
    }
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.getResponse().setHeader("X-Frame-Options", "SAMEORIGIN");
        //判断是否已经放入用户信息,如果已经放入不经过此过滤器。
        HttpServletRequest request = ctx.getRequest();
        if(null == request.getSession().getAttribute(LOGIN_NAME)){
            return true;
        }
        return false;
    }
    @Override
    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();
            SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
            request.getSession().setAttribute(LOGIN_NAME,context.getAuthentication().getPrincipal().toString());
            log.info("AccessFilter--run()--执行");
            return null;
        } catch (Exception e) {
            log.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "AccessFilter", "run()", e.getMessage());
            return null;
        }
    }
}