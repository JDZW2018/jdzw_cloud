package cn.com.myproject.etc.factory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 初始化AmqpAdmin
 * Queue
 * Exchange
 * Binding
 * */
@Component
public class MQInitConfig {

    private static final Logger logger = LoggerFactory.getLogger(MQInitConfig.class);

    private final AmqpAdmin amqpAdmin;

    @Autowired
    public MQInitConfig(AmqpAdmin amqpAdmin){

        this.amqpAdmin = amqpAdmin;

        Queue queue = QueueBuilder.nonDurable("smsone").build();
        Queue queuer = QueueBuilder.nonDurable("smsoner").build();
        Queue pushQueue = QueueBuilder.nonDurable("push").build();

        Exchange exchange = ExchangeBuilder.topicExchange("smsone").durable(false).build();
        Exchange exchanger = ExchangeBuilder.topicExchange("smsoner").durable(false).build();
        Exchange pushExchange = ExchangeBuilder.topicExchange("push").durable(false).build();

        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareQueue(queuer);
        amqpAdmin.declareQueue(pushQueue);
        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareExchange(exchanger);
        amqpAdmin.declareExchange(pushExchange);

        amqpAdmin.declareBinding(
                BindingBuilder.bind(queue).to(exchange).with("smsone.*").noargs()
        );
        amqpAdmin.declareBinding(
                BindingBuilder.bind(queuer).to(exchanger).with("smsoner.*").noargs()
        );
        amqpAdmin.declareBinding(
                BindingBuilder.bind(pushQueue).to(pushExchange).with("push.*").noargs()
        );

    }
}
