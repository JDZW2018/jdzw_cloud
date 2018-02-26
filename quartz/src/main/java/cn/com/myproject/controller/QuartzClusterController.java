package cn.com.myproject.controller;

import cn.com.myproject.service.ISchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * QuartzCluster 分布式修改调度服务的Controller。
 * @author zyh
 * @createtime 2018/1/24 0024
 */
@RestController
public class QuartzClusterController {
    @Autowired
    private ISchedulerService schedulerService;

    /**
     * 每隔多少秒调度一次。
     *
     * @param seconds
     * @return
     */
    @GetMapping("/modify/{seconds}")
    public String modifyStartQuartz(@PathVariable String seconds){
        // eg: 0/10 * * ? * * *
        try {
            schedulerService.schedule("testJobTrigger", "DEFAULT", "0/" + seconds + " * * ? * * *");
        } catch (Exception e) {
            return "Failed";
        }
        return "Successful";
    }

}