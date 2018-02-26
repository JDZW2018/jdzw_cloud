package cn.com.myproject.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClientProp {

    @Value("${server.port}")
    private String serverPort;

    @Value("${from}")
    private String from;

    @Value("${spring.rabbitmq.host}")
    private String springRabbitmqHost;

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSpringRabbitmqHost() {
        return springRabbitmqHost;
    }

    public void setSpringRabbitmqHost(String springRabbitmqHost) {
        this.springRabbitmqHost = springRabbitmqHost;
    }
}
