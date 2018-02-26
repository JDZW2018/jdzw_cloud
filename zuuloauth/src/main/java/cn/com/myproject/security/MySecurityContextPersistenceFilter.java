package cn.com.myproject.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class MySecurityContextPersistenceFilter extends GenericFilterBean {

    static final String FILTER_APPLIED = "my__spring_security_scpf_applied";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final boolean debug = logger.isDebugEnabled();

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (request.getAttribute(FILTER_APPLIED) != null) {
            // ensure that filter is only applied once per request
            chain.doFilter(request, response);
            return;
        }

        request.setAttribute(FILTER_APPLIED, Boolean.TRUE);

        Object contextFromSession = request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);

        if (contextFromSession == null) {
            if (debug) {
                logger.debug("HttpSession returned null object for SPRING_SECURITY_CONTEXT");
            }
            chain.doFilter(request, response);
            return;
        }
        if (!(contextFromSession instanceof SecurityContext)) {
            if (logger.isWarnEnabled()) {
                logger.warn(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY
                        + " did not contain a SecurityContext but contained: '"
                        + contextFromSession
                        + "'; are you improperly modifying the HttpSession directly "
                        + "(you should always use SecurityContextHolder) or using the HttpSession attribute "
                        + "reserved for this class?");
            }
            chain.doFilter(request, response);
            return;
        }

        SecurityContext sc =  (SecurityContext) contextFromSession;
        // 目前先随便塞个值，等具体业务需要什么，再修改
        request.getSession().setAttribute("_test",sc.getAuthentication().getPrincipal().toString());
        chain.doFilter(request, response);

    }
}
