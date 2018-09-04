package com.test.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DemoController {

    /**
     * 2秒后跳转到百度
     * @param response
     * @return
     */
    @RequestMapping("/refesh")
    public String refesh(HttpServletResponse response){
        response.setHeader("Refresh", "2;URL=https://www.baidu.com");
        return "helloworld";
    }
}
