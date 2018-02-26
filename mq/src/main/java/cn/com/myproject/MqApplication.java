package cn.com.myproject;


import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringCloudApplication
public class MqApplication {


	public static void main(String[] args) {
		SpringApplication.run(MqApplication.class, args);
	}

	@Bean(name="receiveConnectionFactory")
	public ConnectionFactory receiveConnectionFactory(
			@Value("${spring.rabbitmq.host}") String host,
			@Value("${spring.rabbitmq.port}") int port,
			@Value("${spring.rabbitmq.username}") String username,
			@Value("${spring.rabbitmq.password}") String password,
			@Value("${spring.rabbitmq.virtual-host}") String virtualHost,
			@Value("${spring.rabbitmq.publisher-returns}")boolean confirms,
			@Value("${spring.rabbitmq.publisher-confirms}")boolean returns,
			@Value("${spring.rabbitmq.cache.channel.size}")int cacheSize){
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		connectionFactory.setPublisherConfirms(confirms);
		connectionFactory.setPublisherReturns(returns);
		connectionFactory.setChannelCacheSize(cacheSize);
		return connectionFactory;
	}

	@Bean(name="sendConnectionFactory")
	@Primary
	public ConnectionFactory snedConnectionFactory(
			@Value("${spring.rabbitmq.host}") String host,
			@Value("${spring.rabbitmq.port}") int port,
			@Value("${spring.rabbitmq.username}") String username,
			@Value("${spring.rabbitmq.password}") String password,
			@Value("${spring.rabbitmq.virtual-host}") String virtualHost,
			@Value("${spring.rabbitmq.publisher-returns}")boolean confirms,
			@Value("${spring.rabbitmq.publisher-confirms}")boolean returns,
			@Value("${spring.rabbitmq.cache.channel.size}")int cacheSize){
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		connectionFactory.setPublisherConfirms(confirms);
		connectionFactory.setPublisherReturns(returns);
		connectionFactory.setChannelCacheSize(cacheSize);
		return connectionFactory;
	}


	@Bean("receiveContainerFactory")
	public SimpleRabbitListenerContainerFactory receiveContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
			@Qualifier("receiveConnectionFactory") ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		//factory.setMessageConverter(myMessageConverter());
		return factory;
	}

	@Bean("sendContainerFactory")
	@Primary
	public SimpleRabbitListenerContainerFactory sendContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
														  @Qualifier("sendConnectionFactory") ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory =
				new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		//factory.setMessageConverter(myMessageConverter());

		return factory;
	}



}



