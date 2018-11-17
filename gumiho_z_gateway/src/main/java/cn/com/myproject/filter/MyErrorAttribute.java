package cn.com.myproject.filter;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * @author tianfusheng
 * @date 2018/5/12
 */
@Component
public class MyErrorAttribute extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> result = super.getErrorAttributes(requestAttributes, includeStackTrace);
        result.put("status", 500);
        result.put("error", "error");
        result.put("exception", "ErrorExtFilter error");
        result.put("message", "访问失败,gateway服务器错误");
        result.put("errorCode","00004");
        return result;
    }
}