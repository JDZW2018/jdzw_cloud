package cn.com.myproject.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaRegistration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyang-macbook on 2016/11/17.
 */
@RestController
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger(DemoController.class);



    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        Integer r = a + b;
        return r;
    }
    @RequestMapping(value = "/add1" ,method = RequestMethod.GET)
    public String add() {
        logger.info("====="+System.currentTimeMillis());
        return "111111111111111";
    }
}

