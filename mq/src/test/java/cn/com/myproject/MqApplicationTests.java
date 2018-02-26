package cn.com.myproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MqApplicationTests {

	@Autowired
	private RabbitTemplate amqpTemplate;
	@Autowired
	private AmqpAdmin amqpAdmin;

	@Test
	public void contextLoads() {
	//	amqpAdmin.declareExchange(ExchangeBuilder.directExchange("foo").build());
		amqpTemplate.send("foo","id.124", MessageBuilder.withBody("11".getBytes()).setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN).build());



	}

}
