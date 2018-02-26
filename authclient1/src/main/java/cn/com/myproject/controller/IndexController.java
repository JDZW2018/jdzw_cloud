package cn.com.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class IndexController {



    @RequestMapping("/")
    public String index1() {
        return "client-index";
    }

    @RequestMapping("/private/index")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "client-index,private";
    }
}
