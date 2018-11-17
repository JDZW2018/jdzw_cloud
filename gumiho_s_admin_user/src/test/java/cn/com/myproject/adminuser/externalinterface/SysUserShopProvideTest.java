package cn.com.myproject.adminuser.externalinterface;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author tianfusheng
 * @date 2018/3/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserShopProvideTest {

    @Autowired
    private SysUserShopProvide provide;

    @Test
    public void selectByUserId() {
        System.out.println(provide.selectByUserId("1"));
    }

    @Test
    public void selectByShopId() {
    }
}