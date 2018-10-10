package cn.com.myproject.filter;

import cn.com.myproject.redis.IRedisService;
import cn.com.myproject.util.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tianfusheng
 * @date 2018/6/1
 */
@Component
public class B2bAccessFilter extends ZuulFilter {
    //B2B商城只b2bStr数组中的URL进行登录验证
    private static final String[] b2bStr = new String[]{"/**/b2b/order/**","/**/b2b/dealOrder/**"};
    private static Logger log = LoggerFactory.getLogger(B2bAccessFilter.class);

    @Autowired
    private IRedisService redisService;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        PathMatchingResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
        String uri = request.getRequestURI();
        for (String s :b2bStr){
            if(resourceLoader.getPathMatcher().match(s, uri)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String sessionId = CookieUtil.getCookie("SESSION",request);
        if(sessionId == null|| StringUtils.isEmpty(sessionId)) {
            log.info("access token is null");
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseBody("{\"result\":\"1\",\"message\":\"tokenIsNull\",\"errorCode\":\"00001\",\"data\":[]}");
            return null;
        }
        String sysUserStr = (String) redisService.getHashValue("spring:session:sessions:"+sessionId,"sessionAttr:"+"sysUserStr");

        if(null==sysUserStr||StringUtils.isEmpty(sysUserStr)){
            log.info("access token timeout");
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseBody("{\"result\":\"1\",\"message\":\"tokenTimeOut\",\"errorCode\":\"00002\",\"data\":[]}");
            return null;
        }
        log.info("access token ok");
        return null;
    }
}
