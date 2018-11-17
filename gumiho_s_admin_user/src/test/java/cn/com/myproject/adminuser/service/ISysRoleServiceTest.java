package cn.com.myproject.adminuser.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author tianfusheng
 * @date 2018/3/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ISysRoleServiceTest {

    @Autowired
    private ISysRoleService service;
    @Test
    public void selectRolesByDepartmentId() {

        System.out.println("++++++"+service.selectRolesByDepartmentId("background"));
    }
}