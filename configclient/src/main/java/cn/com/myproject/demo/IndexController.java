package cn.com.myproject.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyang-macbook on 16/8/31.
 */

@RestController
public class IndexController {
    @RequestMapping("/index")
    public String index(){

        return  "index";
    }
}
