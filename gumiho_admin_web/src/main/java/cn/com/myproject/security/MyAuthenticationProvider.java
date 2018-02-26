package cn.com.myproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by liyang-macbook on 2017/6/21.
 */
@Service
public class MyAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserDetailsService myUserDetailService;

    @PostConstruct
    public void init(){
        super.setPasswordEncoder(new BCryptPasswordEncoder(4));
        super.setUserDetailsService(myUserDetailService);
    }
}
