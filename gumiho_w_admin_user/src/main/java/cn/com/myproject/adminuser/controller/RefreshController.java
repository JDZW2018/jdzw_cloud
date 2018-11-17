package cn.com.myproject.adminuser.controller;

import cn.com.myproject.adminuser.service.IRefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liyang-macbook on 2017/8/16.
 */
@Controller
@RequestMapping("/refresh")
public class RefreshController {

    @Autowired
    private IRefreshService refreshService;


    @RequestMapping("/resource")
    public String resource(HttpServletRequest request) {
        String result =  refreshService.resource();
        request.setAttribute("result",result);
        return "sys/refresh";
    }
}
