package cn.com.myproject.etc.sms.mq;

import cn.com.myproject.aliyun.sms.VO.SmsSendVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mq发送
 * @author ly
 */
@Component
public class MyBean {

    private static final Logger logger = LoggerFactory.getLogger(MyBean.class);

    private final RabbitTemplate amqpTemplate;

    private static final String EX_CHANGE = "smsone" ;


    @Autowired
    public MyBean(RabbitTemplate amqpTemplate){

        this.amqpTemplate = amqpTemplate;

        amqpTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if(ack){
                logger.info("默认消息确认成功");
            }else{
                logger.info("默认消息确认失败,correlationData{},cause{}",correlationData,cause);
                //FIXME 业务处理
                 throw new RuntimeException("发送消息失败");
            }
        });
        amqpTemplate.setMandatory(true);

        amqpTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            //FIXME 业务处理
            logger.info("默认================");
            logger.info("message = " + message);
            logger.info("replyCode = " + replyCode);
            logger.info("replyText = " + replyText);
            logger.info("exchange = " + exchange);
            logger.info("routingKey = " + routingKey);
            logger.info("================");
        });
    }

    public boolean sendMessage(String routingKey, SmsSendVO message){
        try {
            CorrelationData correlationData = new CorrelationData(message.getOutId());
            amqpTemplate.convertAndSend(EX_CHANGE,routingKey,message);
            return true;
        }catch (Exception e){
            logger.error("发送失败",e);
        }

       return false;
    }

    public boolean sendAndReceiveMessage(String routingKey, SmsSendVO message){
        boolean b= (boolean) amqpTemplate.convertSendAndReceive(routingKey,message);
        return b;
    }
}
