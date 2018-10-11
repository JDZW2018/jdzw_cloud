package cn.com.myproject.adminuser.service.impl;

import cn.com.myproject.adminuser.po.SysUserRole;
import cn.com.myproject.adminuser.service.ISysUserRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author tianfusheng
 * @date 2018/3/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserRoleServiceTest {
    @Autowired
    private ISysUserRoleService service;
    @Test
    public void addSysUserRole() {
    }

    @Test
    public void updateSysUserRole() {
    }

    @Test
    public void findSysUserRoleByUserId() {
        String s = "6804f23b79094806911d45e67fc292f5";
         List<SysUserRole> sysUserRoleList=service.findSysUserRoleByUserId(s);
        System.out.println("=============="+sysUserRoleList.toString());
    }

    @Test
    public void deleteSysUserRole() {
    }

    @Test
    public void batchAddSysUserRole() {
    }
}
