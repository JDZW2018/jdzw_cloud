package cn.com.myproject.adminuser.service.impl;


import cn.com.myproject.adminuser.service.ISysUserService;
import cn.com.myproject.adminuser.vo.SysUserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author tianfusheng
 * @date 2018/2/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceTest {
    @Autowired
    private ISysUserService sysUserService;
    @Test
    public void getByLoginName() {
        SysUserVO sysUser =  sysUserService.getByLoginName("honglan1009");
        System.out.println(sysUser.getPassword());
    }
}
