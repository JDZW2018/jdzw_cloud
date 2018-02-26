package cn.com.myproject.security;


import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by liyang-macbook on 2017/6/21.
 */
@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {


//    @Autowired
//    private IRedisService redisService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
//        Set<Object> set = redisService.getKey(WebSecurityConfig.URL_SECURITY_KEY);
//
//        for(Object key : set) {
//            RequestMatcher requestMatcher = new AntPathRequestMatcher(key.toString());
//            if(requestMatcher.matches(filterInvocation.getHttpRequest())) {
//                return (Collection<ConfigAttribute>) redisService.getHashValue(WebSecurityConfig.URL_SECURITY_KEY,key.toString());
//            }
//        }
//
//        return null;
        List<ConfigAttribute> configAttributes = new ArrayList<>();

        Set<Object> set = new HashSet<>();
        set.add("/private/**");
        for(Object key : set) {
            RequestMatcher requestMatcher = new AntPathRequestMatcher(key.toString());
            if(requestMatcher.matches(filterInvocation.getHttpRequest())) {
                configAttributes.add(new SecurityConfig("USER"));
                return configAttributes;
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
