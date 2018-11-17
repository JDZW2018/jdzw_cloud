package cn.com.myproject.adminuser.service;

import cn.com.myproject.adminuser.po.SysUserDepartment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author tianfusheng
 * @date 2018/3/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ISysUserDepartmentServiceTest {

    @Autowired
    private ISysUserDepartmentService service;

    @Test
    public void addSysUserDepartment() {
        SysUserDepartment sysUserDepartment = new SysUserDepartment();
        sysUserDepartment.setDepartmentId("1");
        sysUserDepartment.setUserId("1");
        sysUserDepartment.setStatus((short)1);
        sysUserDepartment.setVersion(1);

        service.addSysUserDepartment(sysUserDepartment);
    }

    @Test
    public void deleteByUserId() {
        service.deleteByUserId("1");
    }

    @Test
    public void selectByDepartmentId() {
        List<SysUserDepartment> list= service.selectByDepartmentId("departmentid");
        System.out.println("++++++++++++++++"+list);

    }

    @Test
    public void selectByUserId() {
        System.out.println("++++++++++++++++"+service.selectByUserId("userid"));
    }
}