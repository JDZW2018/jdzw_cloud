package cn.com.myproject.adminuser.service;

import cn.com.myproject.adminuser.po.SysRoleDepartment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author tianfusheng
 * @date 2018/3/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ISysRoleDepartmentServiceTest {

    @Autowired
    private ISysRoleDepartmentService service;
    @Test
    public void addSysRoleDepartment() {
        SysRoleDepartment sysRoleDepartment = new SysRoleDepartment();
        sysRoleDepartment.setDepartmentId("1");
        sysRoleDepartment.setRoleId("1");
        sysRoleDepartment.setStatus((short)1);
        sysRoleDepartment.setVersion(1);
        service.addSysRoleDepartment(sysRoleDepartment);
    }

    @Test
    public void deleteByRoleId() {
        service.deleteByRoleId("1");
    }

    @Test
    public void selectByDepartmentId() {
       List<SysRoleDepartment> list = service.selectByDepartmentId("2");
        System.out.println(list);
    }
}