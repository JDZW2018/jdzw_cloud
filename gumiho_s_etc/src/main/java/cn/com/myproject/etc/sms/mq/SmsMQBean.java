package cn.com.myproject.etc.sms.mq;

import cn.com.myproject.aliyun.sms.AliyunSmsService;
import cn.com.myproject.aliyun.sms.VO.SmsSendVO;
import cn.com.myproject.etc.sms.entity.SmsSendOne;
import cn.com.myproject.etc.sms.mapper.SmsSendOneMapper;
import cn.com.myproject.etc.util.SmsSendVOConvert;
import com.aliyuncs.exceptions.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class SmsMQBean {

    private static final Logger logger = LoggerFactory.getLogger(SmsMQBean.class);

    @Autowired
    private SmsSendOneMapper smsSendOneMapper;

    @Autowired
    private AliyunSmsService aliyunSmsService;

    @RabbitListener(queues = "smsoner")
    @SendTo
    public boolean processMessager(Message message,SmsSendVO vo){
        logger.info("接收消息信息：phoneNum:{},outId:{}",vo.getPhoneNum(),vo.getOutId());
       // throw new RuntimeException("11111");
        return true;
    }

    @RabbitListener(queues = "smsone")
    public void processMessage(Message message,SmsSendVO vo){
        logger.info("接收消息信息：phoneNum:{},outId:{}",vo.getPhoneNum(),vo.getOutId());
        int count = smsSendOneMapper.countByOutId(vo.getOutId());
        if(count != 0) {
            logger.info("消息已处理：phoneNum:{},outId:{}",vo.getPhoneNum(),vo.getOutId());
            return;
        }
        SmsSendOne one = SmsSendVOConvert.convertOne(vo);
        int i = smsSendOneMapper.insert(one);
        if(i != 1) {
            logger.error("插入失败：phoneNum:{},outId:{}",vo.getPhoneNum(),vo.getOutId());
            //FIXME
            //throw new RuntimeException("插入失败");
            return;
        }
        Map<String,String > map = null;
        try {
            map = aliyunSmsService.sendSms(one.getPhoneNum(),one.getSignName(),one.getTemplateCode(),
                    one.getTemplateParam(),vo.getOutId());
            logger.info(map.toString());
        } catch (ClientException e) {
            logger.error("发送短信异常",e);
            throw new RuntimeException("发送短信异常");
        }
        SmsSendOne _one  = new SmsSendOne();
        _one.setOutId(one.getOutId());
        _one.setRequestId(map.get("requestId"));
        _one.setBizId(map.get("bizId"));
        _one.setCode(map.get("code"));
        _one.setMessage(map.get("message"));
        i = smsSendOneMapper.update(_one);
        if(i != 1) {
            logger.error("更新短信结果失败：outId:{},requestId:{},bizId:{}",vo.getOutId(),map.get("requestId"),map.get("bizId"));
            throw new RuntimeException("更新短信结果失败");
        }
        //FIXME 等业务发展，看是否监控回执

    }

}
