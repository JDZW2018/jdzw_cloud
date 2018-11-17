package cn.com.myproject.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;


/**
 * @author tianfusheng
 * @date 2018/5/12
 */
@Component
public class ErrorFilter extends ZuulFilter {
    private Logger log = LoggerFactory.getLogger(ErrorFilter.class);
    @Override
    public String filterType() {
        return "error";
    }
    @Override
    public int filterOrder() {
        return -1;
    }
    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().containsKey("throwable");
    }
    @Override
    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            Object e = ctx.get("throwable");

            if (e != null && e instanceof ZuulException) {
                ZuulException zuulException = (ZuulException) e;
                log.error("异常获取",e);
                // Remove error code to prevent further error handling in follow up filters
                // 删除该异常信息,不然在下一个过滤器中还会被执行处理
                ctx.remove("throwable");
                // 根据具体的业务逻辑来处理
                ctx.setResponseBody("{\"result\":1,\"message\":\"访问失败,项目正在构建\",\"errorCode\":\"00003\",\"data\":[]}");
                ctx.getResponse().setContentType("text/html;charset=UTF-8");
                ctx.setResponseStatusCode(HttpStatus.OK.value());
            }
        } catch (Exception ex) {
            log.error("[{}]-[{}]时，发生异常，异常信息为[{}]", "ErrorFilter", "run()", ex.getMessage());
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

}