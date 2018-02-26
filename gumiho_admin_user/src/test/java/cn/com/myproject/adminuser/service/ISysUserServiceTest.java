package cn.com.myproject.adminuser.service;

import cn.com.myproject.adminuser.vo.SysUserVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ISysUserServiceTest {


    @Autowired
    private ISysUserService sysUserService;

    @Test
    public void getByLoginName() throws Exception {
        SysUserVO vo = sysUserService.getByLoginName("honglan1009");
        Assert.assertNotNull(vo.getLoginName(),vo);
    }

    @Test
    public void getByUserName() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void getPage() throws Exception {
    }

    @Test
    public void addUsers() throws Exception {
    }

    @Test
    public void updateUsers() throws Exception {
    }

    @Test
    public void delusers() throws Exception {
    }

    @Test
    public void findByUserId() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
    }

    @Test
    public void checkUsers() throws Exception {
    }

    @Test
    public void selectUsers() throws Exception {
    }

    @Test
    public void getSysUserByLoginName() throws Exception {
    }

    @Test
    public void getSysUserByUserName() throws Exception {
    }

}