package cn.com.myproject.etc.push.mq;

import cn.com.myproject.aliyun.push.VO.PushInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mq发送
 * @author ly
 */
@Component
public class PushMQSend {

    private static final Logger logger = LoggerFactory.getLogger(PushMQSend.class);

    private  final RabbitTemplate amqpTemplate;

    private static final String EX_CHANGE = "push" ;

    @Autowired
    public PushMQSend(RabbitTemplate amqpTemplate){

        this.amqpTemplate = amqpTemplate;
    }


    public boolean sendMessage(String routingKey, PushInfoVO pushInfoVO){
        try {
            amqpTemplate.convertAndSend(EX_CHANGE,routingKey,pushInfoVO);
            return true;
        }catch (Exception e){
            logger.error("发送失败",e);
        }

        return false;
    }

}
