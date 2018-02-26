package cn.com.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class IndexController {



    @Autowired
    private OAuth2RestTemplate oauth2RestTemplate;

    @RequestMapping("/")
    public String index1() {
        return "client-index";
    }

    @RequestMapping("/private/index")
    public String index() {
        return "client-index,private";
    }

    @RequestMapping("/private/t1")
    public String private1index(HttpServletRequest request, HttpServletResponse response) {
        DefaultOAuth2ClientContext context = (DefaultOAuth2ClientContext) request.getSession().getAttribute("scopedTarget.oauth2ClientContext");
        return "client-index,t1,"+context.getAccessToken().getValue();
    }
}
