package com.test.userLoginTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Tianfusheng
 * @date 2018/9/8
 */
@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService service;
    @RequestMapping("/login")
    public String login(String name , String pwd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = service.login(name, pwd);
        if(null != user){
            String username = user.getUsername();
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            response.sendRedirect("/User/index");
            return "";
        }else {
            return "login Info error";
        }
    }
    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        Object username = request.getSession().getAttribute("username");
        return "ok! welcome: "+username;
    }

}
