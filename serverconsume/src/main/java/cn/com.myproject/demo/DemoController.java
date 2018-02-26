package cn.com.myproject.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyang-macbook on 2016/11/17.
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @Autowired
    private DemoFeignClient demoFeignClient;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return demoService.addService();
    }

    @RequestMapping(value = "/add1", method = RequestMethod.GET)
    public Integer add1() {
        return demoFeignClient.add(2,1);
    }
}
