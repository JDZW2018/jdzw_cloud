package cn.com.myproject.etc.push.provide;

import cn.com.myproject.aliyun.push.AliyunPushService;
import cn.com.myproject.aliyun.push.VO.PushInfoVO;
import cn.com.myproject.etc.factory.IDSingleton;
import cn.com.myproject.etc.push.entity.PushInfo;
import cn.com.myproject.etc.push.mq.PushMQBean;
import cn.com.myproject.etc.push.mq.PushMQSend;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tianfusheng
 * @date 2018/4/28
 */
@RestController
@RequestMapping("/push")
public class PushController {
    @Autowired
    private AliyunPushService pushService;
    @Autowired
    private PushMQSend pushMQSend;
    @Autowired
    private PushMQBean pushMQBean;
    @GetMapping("/demo")
    public void demo(){
        Map<String, String> map = pushService.push("hello","world","ALL","ALL","ALL","MESSAGE","","USER");
        System.out.println(map.toString());
    }
    @PostMapping("/pushMessage")
    public String pushMessage(@RequestParam("routingKey") String routingKey , @RequestBody PushInfoVO pushInfoVO){
        String result = null;
        pushInfoVO.setOutId(IDSingleton.getInstance().nextId());
        boolean b =  pushMQSend.sendMessage(routingKey,pushInfoVO);
        if(b){
            result = "success";
            return result;
        }
        result = "error";
        return  result;
    }

    /**
     * 修改信息状态
     * @param targetValue
     * @return
     */
    @PostMapping("/updateMessageStatus")
    public String updateMessageStatus(String targetValue ,Long outId){
        String result = "0";
        boolean b = pushMQBean.updateMessageStatus(targetValue,outId);
        if(b){
            result="1";
        }
       return  result;
    }

    /**
     * 查询所有信息
     * @return
     */
    @PostMapping("/selectMessage")
    public List<PushInfoVO> selectMessage(String targetValue){
       List<PushInfo> pushInfoList = pushMQBean.selectMessage(targetValue);
       List<PushInfoVO> pushInfoVOList = new ArrayList<>();
        if (pushInfoList != null && pushInfoList.size()>0) {
            PushInfoVO pushInfoVO = null;
            for (PushInfo pushInfo:pushInfoList){
                if(pushInfo!=null){
                    pushInfoVO = new PushInfoVO();
                    BeanUtils.copyProperties(pushInfo,pushInfoVO);
                    pushInfoVOList.add(pushInfoVO);
                }
            }
        }
        return pushInfoVOList;
    }
}
