package cn.com.myproject.demo;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.Date;

/**
 * @author tianfusheng
 * @date 2018/1/20
 */
@Component("TestTask")
public class TestTask {
/*    @Autowired
    private RabbitTemplate amqpTemplate;
    @Autowired
    private AmqpAdmin amqpAdmin;*/
    @Autowired
    private MyBean myBean;

    @Scheduled(cron = "*/1 * * * * ?")
    public void testTask() {
        System.out.println("spring cloud task [1]  "+ new Date());

      /*  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date factInPlaceDate = new Date();

        Calendar beforeCd = Calendar.getInstance();
        beforeCd.setTime(factInPlaceDate);
        beforeCd.add(Calendar.MONTH, -1);
        Date beforeFactDate = beforeCd.getTime();
        String beforeMonthDate = sdf.format(beforeFactDate);
        System.out.println("实际到位日期减一个月：" + beforeMonthDate);

        Calendar afterCd = Calendar.getInstance();
        afterCd.setTime(factInPlaceDate);
        afterCd.add(Calendar.MONTH, +1);
        Date afterFactDate = afterCd.getTime();
        String afterMonthDate = sdf.format(afterFactDate);
        System.out.println("实际到位日期加一个月：" + afterMonthDate);*/
    }
    @Scheduled(cron = "*/5 * * * * ?")//五秒执行一次
    public  void testTask1(){
        System.out.println("spring cloud task [5]  "+ new Date());
    }
    @Scheduled(cron = "1 * * * * ?")//1分钟 执行一次
    public  void testTask2(){
        System.out.println("spring cloud task [60]  "+ new Date());//
    }
    @Scheduled(cron = "*/1 * * * * ?")//每秒执行一次向mq发送消息。
    public void testTask3(){
        myBean.sendMessage("tfs","tfs_key","spring cloud task test. 信息同步消息。-------");
    }

}