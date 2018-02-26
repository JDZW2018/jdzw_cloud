package cn.com.myproject.security;

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
public class MyUserDetailService implements UserDetailsService {
    
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //FIXME 需要改为调用服务
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_TEST,USER");
        authorities.add(authority);
        return new User(s,"ly",authorities);
    }
}
