package cn.com.myproject.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyang-macbook on 2016/11/15.
 */
@RefreshScope
@RestController
@RequestMapping(value = "/config")
public class DemoController {

    @Value("${from}")
    private String from;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private ConfigClientProp configClientProp;

    @RequestMapping("/from")
    public String from() {
        return this.from;
    }
    @RequestMapping("/lei")
    public String lei() {
        return "hello  configclient-dev.yml server.port=" + serverPort + " configClientProp对象读取 serverPort = "+ configClientProp.getServerPort();
    }
}
