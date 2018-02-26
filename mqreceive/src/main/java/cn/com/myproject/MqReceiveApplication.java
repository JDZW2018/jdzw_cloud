package cn.com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class MqReceiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqReceiveApplication.class, args);
    }
}
