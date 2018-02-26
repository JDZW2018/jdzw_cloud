package cn.com.myproject.demo;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liyang-macbook on 2016/12/12.
 */
@Component
public class MyBean {

    private final AmqpAdmin amqpAdmin;

    private final RabbitTemplate amqpTemplate;

    @Autowired
    public MyBean(AmqpAdmin amqpAdmin,RabbitTemplate amqpTemplate){
        this.amqpAdmin = amqpAdmin;
        this.amqpTemplate = amqpTemplate;


        amqpTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if(ack){
                System.out.println("消息确认成功");
            }else{
                System.out.println("消息确认失败");
            }
        });
        amqpTemplate.setMandatory(true);

        amqpTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("================");
            System.out.println("message = " + message);
            System.out.println("replyCode = " + replyCode);
            System.out.println("replyText = " + replyText);
            System.out.println("exchange = " + exchange);
            System.out.println("routingKey = " + routingKey);
            System.out.println("================");
        });
        Queue queue1 = QueueBuilder.nonDurable("demo1").build();
        Queue queue2 = QueueBuilder.nonDurable("demo2").build();
        Queue queue3 = QueueBuilder.nonDurable("demo3").build();

        Exchange exchange1 = ExchangeBuilder.topicExchange("demo1").durable(false).build();
        Exchange exchange2 = ExchangeBuilder.directExchange("demo2").durable(false).build();
        FanoutExchange exchange3 = (FanoutExchange) ExchangeBuilder.fanoutExchange("demo3").durable(false).build();

        amqpAdmin.declareQueue(queue1);
        amqpAdmin.declareQueue(queue2);
        amqpAdmin.declareQueue(queue3);
        amqpAdmin.declareExchange(exchange1);
        amqpAdmin.declareExchange(exchange2);
        amqpAdmin.declareExchange(exchange3);

        amqpAdmin.declareBinding(
                    BindingBuilder.bind(queue1).to(exchange1).with("demo1.*").noargs()
                );

        amqpAdmin.declareBinding(
                BindingBuilder.bind(queue2).to(exchange2).with("demo2").noargs()

        );
        amqpAdmin.declareBinding( BindingBuilder.bind(queue3).to(exchange3));


    }

    public boolean sendMessage(String exchange,String routingKey, String message){
//        Message message1 = amqpTemplate.sendAndReceive(exchange,routingKey, MessageBuilder.withBody(message.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN).build());
//        System.out.println("======,"+new String(message1.getBody()));
       amqpTemplate.send(exchange,routingKey, MessageBuilder.withBody(message.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN).build());

        return true;
    }

//    @RabbitListener(queues = "demo2.reply",containerFactory = "receiveContainerFactory")
//    public void processMessage1(Message message,String content){
//        System.out.println(content+",reply-------------------------------------------------");
//    }

}
