package cn.com.myproject.adminuser.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liyang-macbook on 2017/6/22.
 */
@Controller
public class IndexController {

    @RequestMapping({"/index","/"})
    public String index(HttpServletRequest httpServletRequest){
        return "index";
    }

}
