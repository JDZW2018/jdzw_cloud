package cn.com.myproject;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.slf4j.Logger;
/**
 * Quartz-Cluster 微服务，支持集群分布式，并支持动态修改 Quartz 任务的 cronExpression 执行时间。
 * @author zyh
 * @createtime 2018/1/24 0024
 */

@SpringBootApplication
@ImportResource("quartz.xml")
public class SimpleQuartzClusterApplication {
    private static final Logger Logger = LoggerFactory.getLogger(SimpleQuartzClusterApplication.class);

    public static void main(String[] args) {
        Logger.info("简单Quartz-Cluster微服务入口函数编码-" + System.getProperty("file.encoding"));

        SpringApplication.run(SimpleQuartzClusterApplication.class, args);

        System.out.println("【【【【【【 简单Quartz-Cluster微服务 】】】】】】已启动.");
    }

}
