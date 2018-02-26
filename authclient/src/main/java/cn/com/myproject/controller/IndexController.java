package cn.com.myproject.controller;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class IndexController {

    @Resource
    private OAuth2RestTemplate oAuth2RestTemplate;

    @RequestMapping("/")
    public String index1() {
        String str = oAuth2RestTemplate.getForObject("http://zuul.dh.com:3335/private/index",String.class);
        return "client-index";
    }

    @RequestMapping("/private/index")
    public String index() {
        return "client-index,private";
    }
}
