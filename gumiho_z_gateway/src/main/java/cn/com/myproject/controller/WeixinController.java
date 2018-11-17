package cn.com.myproject.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信公众号安全设置验证
 * @author liyang-macbook
 */
@RestController
public class WeixinController {

    @RequestMapping(method = RequestMethod.GET,value = "/MP_verify_gBFEbI2VY84Zj3pP.txt")
    public String weixingongzhognhao() {
        return "gBFEbI2VY84Zj3pP";
    }
}
