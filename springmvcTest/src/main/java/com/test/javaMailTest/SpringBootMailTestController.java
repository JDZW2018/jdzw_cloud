package com.test.javaMailTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: tianfusheng
 * @Date: 2018/9/11 16:42
 * @Description:
 */
@RestController
public class SpringBootMailTestController {

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping("/sendMail")
    public String sendMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("949465194@qq.com");
        message.setTo("t9vg@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        javaMailSender.send(message);
        return "success";
    }

}
