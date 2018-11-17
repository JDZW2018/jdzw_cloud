package cn.com.myproject.security;


import cn.com.myproject.oauth2.SecurityConfiguration;
import cn.com.myproject.redis.IRedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by liyang-macbook on 2017/6/21.
 */
@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IRedisService redisService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        if(filterInvocation.getRequest().getRequestURI().equals("/")){
            return  null;
        }
        Set<Object> set = redisService.getKey(SecurityConfiguration.URL_SECURITY_KEY);
        String url = filterInvocation.getRequestUrl();
        String resURL = null;
        if(StringUtils.contains(url,"?")){//截取带参数URL
            url = StringUtils.substringBefore(url,"?");
        }
        for(Object key : set){
            resURL=key.toString();
            if(url.equals(resURL)){
                return (Collection<ConfigAttribute>) redisService.getHashValue(SecurityConfiguration.URL_SECURITY_KEY,key.toString());
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<ConfigAttribute>();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
