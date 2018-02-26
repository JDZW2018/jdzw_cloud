package cn.com.myproject.task;


import cn.com.myproject.demo.MyBean;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

/**
 * 调度的任务。
 *
 * testScheduleTask 字符串名称在 quartz.xml 中配置为属性 targetObject 的 value 值。</li>
 * sayHello 方法名称在 quartz.xml 中配置为属性 targetMethod 的 value 值。</li>
 *
 * @author zyh
 * @createtime 2018/1/24 0024
 */

@Configuration
@Component("testScheduleTask")
@EnableScheduling
public class ScheduleTask {

    static final Logger Logger = org.slf4j.LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
     private MyBean myBean;

    public void sayHello(JobExecutionContext context){
      Logger.info("====    sayHello 123456789    ====");
        System.out.println("====    sayHello 123456789    ====");
        myBean.sendMessage("tfs","tfs_key","spring cloud task test. 信息同步消息。-------");
    }
}