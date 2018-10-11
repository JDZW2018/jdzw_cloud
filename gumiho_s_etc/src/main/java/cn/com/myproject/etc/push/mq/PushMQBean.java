package cn.com.myproject.etc.push.mq;

import cn.com.myproject.aliyun.push.AliyunPushService;
import cn.com.myproject.aliyun.push.VO.PushInfoVO;
import cn.com.myproject.etc.config.Constants;
import cn.com.myproject.etc.push.entity.PushInfo;
import cn.com.myproject.etc.push.mapper.PushInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * @author tianfusheng
 * @date 2018/4/28
 */
@Component
public class PushMQBean {
    private static final Logger logger = LoggerFactory.getLogger(PushMQBean.class);

    @Autowired
    private AliyunPushService pushService;
    @Autowired
    private PushInfoMapper pushInfoMapper;

    @RabbitListener(queues = "push")
    public void processMessage(PushInfoVO pushInfoVO) {
        logger.info("接收消息信息：标题:{},outId:{}",pushInfoVO.getTitle(),pushInfoVO.getOutId());
        Integer count = pushInfoMapper.countByOutId(pushInfoVO.getOutId());
        if(count != 0){
            logger.info("消息已处理：标题:{},outId:{}",pushInfoVO.getTitle(),pushInfoVO.getOutId());
            return;
        }
        PushInfo pushInfo = new PushInfo();
        BeanUtils.copyProperties(pushInfoVO,pushInfo);
        pushInfo.setMessageStatus((short) 0);
        pushInfo.setCreateTime(System.currentTimeMillis());
        pushInfo.setVersion(1);
        int i = pushInfoMapper.insert(pushInfo);
        if(i != 1) {
            logger.error("插入失败：标题:{},outId:{}",pushInfoVO.getTitle(),pushInfoVO.getOutId());
            return;
        }
        logger.info("插入成功：标题:{},outId:{}",pushInfoVO.getTitle(),pushInfoVO.getOutId());
        Map<String, String> map = null;
        try {
            map = pushService.push(pushInfoVO.getTitle(), pushInfoVO.getBody(), pushInfoVO.getTarget(), pushInfoVO.getTargetValue(), pushInfoVO.getDeviceType(), pushInfoVO.getPushType(), pushInfoVO.getExtParameters(),pushInfoVO.getApps());
            logger.info("推送成功！");
            logger.info(map.toString());
        } catch (Exception e) {
            logger.error("发生异常",e);
            throw new  RuntimeException("推送失败！");
        }
        if(map.get(Constants.STATUS).equals(Constants.FAIL)){
            logger.error("推送发回结果失败！");
            return;
        }
        if(map.get(Constants.STATUS).equals(Constants.SUCCESS)){
            logger.info("更新messageStatus");
            PushInfo pushInfo1 = new PushInfo();
            pushInfo1.setOutId(pushInfo.getOutId());
            pushInfo1.setMessageStatus((short)1);
            pushInfo1.setVersion(pushInfo.getVersion()+1);
            int i1 = pushInfoMapper.updateByOutIdSelective(pushInfo1);
            if(i1 != 1){
                logger.error("push成功,但更新messageStatus失败,标题:{},outId:{}",pushInfoVO.getTitle(),pushInfoVO.getOutId());
                throw new RuntimeException("push成功,但更新messageStatus失败");
            }
        }
    }
    public boolean updateMessageStatus(String targetValue,Long outId){
        boolean flag = false;
        int i = pushInfoMapper.updateMessageStatus(targetValue,outId);
        if(i>0){
            flag =true;
        }
            return flag;
    }

    public List<PushInfo> selectMessage(String targetValue) {
      return  pushInfoMapper.selectMessage(targetValue);
    }
}
