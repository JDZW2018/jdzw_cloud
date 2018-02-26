package cn.com.myproject.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianfusheng
 * @date 2018/1/20
 * 运行测试error数据库记录状态码.
 */

@SpringCloudApplication
//@EnableTask
public class SpringCloudTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudTaskApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new TestCommandLineRunner();
    }

    public static class TestCommandLineRunner implements CommandLineRunner {
        @Override
        public void run(String... strings) throws Exception {
            System.out.println("this is a Test about spring cloud task.");
            try{
                List<String> list = new ArrayList<>();
                list.get(1);
            }catch (Exception e){
                System.out.println("Error");
                throw e;
            }
        }
    }
}