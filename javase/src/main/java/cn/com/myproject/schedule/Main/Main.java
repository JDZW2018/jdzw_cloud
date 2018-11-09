package cn.com.myproject.schedule.Main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * scheduleAtFixedRate(Runnable command,long initialDelay,long period,TimeUnit unit);
 * 第一个参数：具体执行的任务
 * 第二个参数：首次执行要延迟的时间
 *
 * 第三个参数：任务执行间隔
 *
 * 第四个参数：间隔时间单位
 */
public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        System.out.println("当前时间：" + new Date());
        //上次结束后推辞设置时间执行。
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务（1）运行了,当前时间：" + new Date());
                Date d = new Date();
                long d2 = d.getTime() - (1000 * 3600 * 24*30L);
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String date = sdf.format(d2);
                System.out.println(date);
                long  nowt =System.currentTimeMillis();
            }
        }, 1, 3, TimeUnit.SECONDS);
        //按原定计划执行
/*        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务（2）运行了，当前时间:" + new Date());
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM");
                Long l = System.currentTimeMillis();
                String date = sdf.format(System.currentTimeMillis());
                System.out.println(date);
            }
        }, 1, 3, TimeUnit.SECONDS);*/

    }
}