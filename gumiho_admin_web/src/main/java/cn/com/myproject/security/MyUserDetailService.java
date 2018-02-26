package cn.com.myproject.security;

import cn.com.myproject.adminuser.vo.SysUserVO;
import cn.com.myproject.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private ISysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        SysUserVO user = userService.getByLoginName(loginName);
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + loginName + " not found");
        }
        SimpleGrantedAuthority authority;
        if (null != user.getRoleIds() && !user.getRoleIds().isEmpty()) {
            for (String roleId : user.getRoleIds()) {
                authority = new SimpleGrantedAuthority(roleId);
                authorities.add(authority);
            }
        }
        return new SecurityUser(user.getUserId(),loginName, user.getPassword(), authorities);
    }
}
