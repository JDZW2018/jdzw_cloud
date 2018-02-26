package cn.com.myproject.security;

import cn.com.myproject.redis.IRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 此方法为验证方法上的权限控制，过滤带hasPermission属性
 *
 * hasPermission(Object target, Object permission)
 * 如果用户已访问给定权限的提供的目标，则返回true，例如hasPermission(domainObject, 'read')
 * hasPermission(Object targetId, String targetType, Object permission)
 * 如果用户已访问给定权限的提供的目标，则返回true,例如hasPermission(1, 'com.example.domain.Message', 'read')
 *
 * 目前只取permission参数
 * Created by liyang-macbook on 2017/6/26.
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    private static final Logger logger = LoggerFactory.getLogger(CustomPermissionEvaluator.class);

    @Autowired
    private IRedisService redisService;


    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return authorize(authentication.getAuthorities(), (String) permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return authorize(authentication.getAuthorities(), (String) permission);
    }


    public boolean authorize(Collection<? extends GrantedAuthority> auth, String permission) {
        if(logger.isDebugEnabled()){
            logger.debug("进入方法验证");
        }
        if(null==auth || auth.isEmpty()){
            return false;
        }
        List<String> resouces = (List<String>) redisService.getHashValue(WebSecurityConfig.METHOD_SECURITY_KEY,permission);
        if( resouces == null || resouces.isEmpty() ) {
            return false;
        }
        //FIXME 改进算法
        for(String str:resouces){
            for(GrantedAuthority authority:auth){
                if(authority.getAuthority().equals(str)){
                    return true;
                }
            }
        }

        return false;

    }
}
