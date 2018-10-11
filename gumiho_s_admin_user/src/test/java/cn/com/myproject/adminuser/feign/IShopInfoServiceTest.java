package cn.com.myproject.adminuser.feign;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author tianfusheng
 * @date 2018/5/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IShopInfoServiceTest {
    @Autowired
    private IShopInfoService shopInfoService;

    @Test
    public void o2oStatusUpdate() {
        shopInfoService.o2oStatusUpdate("222",1);
    }

    @Test
    public void serviceStatusUpdate() {
    }

    @Test
    public void b2cStatusUpdate() {
    }

    @Test
    public void b2bStatusUpdate() {
    }

    @Test
    public void b2bNaStatusUpdate() {
    }

    @Test
    public void operatingStatusUpdate() {
    }
}