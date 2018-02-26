package cn.com.myproject.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liyang-macbook on 2016/12/1.
 */
@RestController
public class IndexController {


    @RequestMapping("/")
    public String index(HttpServletRequest request){
        return "zuuloauth-index";
    }
    @RequestMapping("/private/index")
    public String index1(HttpServletRequest request){
        SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        return "zuuloauth-index,private";
    }
}
