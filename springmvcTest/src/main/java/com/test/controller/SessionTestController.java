package com.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Auther: Administrator
 * @Date: 2018/9/5 10:57
 * @Description:
 */
@RestController
@RequestMapping("/SessionTest")
public class SessionTestController {
    @RequestMapping("/setSession")
    public void setSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("name","tianfusheng");
    }

    @RequestMapping("/getSession")
    public String getSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        return name;
    }

}
