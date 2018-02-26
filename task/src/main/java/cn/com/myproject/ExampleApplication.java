package cn.com.myproject;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author tianfusheng
 * @date 2018/1/20
 */
@SpringCloudApplication
//@EnableTask//开启一次执行。spring cloud task 自身不支持任务监听，因为执行完成后就关闭进程了，所以一般有外部调度系统控制启动
public class ExampleApplication  {




    @Bean//task入口
    public CommandLineRunner commandLineRunner() {
        return strings ->
                System.out.println("Executed at :" +
                        new SimpleDateFormat().format(new Date()));
    }




    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}