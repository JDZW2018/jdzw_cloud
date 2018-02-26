package cn.com.myproject.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyang-macbook on 2016/12/12.
 */
@RestController
public class MessageController {

    @Autowired
    private MyBean myBean;

    @RequestMapping("/add")
    public String add1(String exchange,String routingKey,String message){
         myBean.sendMessage(exchange,routingKey, message);
         return "ok";
    }


}
