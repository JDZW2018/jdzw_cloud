package cn.com.myproject.security;


import cn.com.myproject.adminuser.vo.SysUserVO;
import cn.com.myproject.redis.IRedisService;
import cn.com.myproject.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by liyang-macbook on 2016/11/23.
 */
@Service
public class RememberUserDetailService implements UserDetailsService {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private IRedisService redisService;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {

        if(StringUtils.isBlank(token)){
            throw new UsernameNotFoundException("UserName " + token + " not found");
        }
        String loginName = redisService.getValue(token);

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        SysUserVO user = sysUserService.getByLoginName(loginName);
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + loginName + " not found");
        }
        SimpleGrantedAuthority authority;
        if(null != user.getRoleIds() && !user.getRoleIds().isEmpty()){
            for(String roleId:user.getRoleIds()){
                authority = new SimpleGrantedAuthority(roleId);
                authorities.add(authority);
            }
        }
        return new User(loginName,user.getPassword(),authorities);
    }
}
