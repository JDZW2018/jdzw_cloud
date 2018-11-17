package cn.com.myproject.etc.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

//@Configuration
@Deprecated
public class MQConfiguration {


    private CachingConnectionFactory createConnectionFactory(String host, int port, String username, String password,
                                                             String virtualHost, boolean confirms, boolean returns,
                                                             int cacheSize) {
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

    @Autowired
    private RabbitProperties rabbitProperties;

    @Bean(name="cachingConnectionFactory")
    @Primary
    public CachingConnectionFactory cachingConnectionFactory(){
        return createConnectionFactory(rabbitProperties.getHost(),rabbitProperties.getPort(),rabbitProperties.getUsername(),
                rabbitProperties.getPassword(),rabbitProperties.getVirtualHost(),rabbitProperties.isPublisherConfirms(),
                rabbitProperties.isPublisherConfirms(),rabbitProperties.getCache().getChannel().getSize());
    }

    @Bean(name="receiveConnectionFactory")
    public ConnectionFactory receiveConnectionFactory(
            @Value("${spring.rabbitmq.receive.host}") String host,
            @Value("${spring.rabbitmq.receive.port}") int port,
            @Value("${spring.rabbitmq.receive.username}") String username,
            @Value("${spring.rabbitmq.receive.password}") String password,
            @Value("${spring.rabbitmq.receive.virtual-host}") String virtualHost,
            @Value("${spring.rabbitmq.receive.publisher-returns}")boolean confirms,
            @Value("${spring.rabbitmq.receive.publisher-confirms}")boolean returns,
            @Value("${spring.rabbitmq.receive.cache.channel.size}")int cacheSize){

        return createConnectionFactory(host,port,username,password,virtualHost,confirms,returns,cacheSize);
    }



    @Bean(name="tranConnectionFactory")
    public ConnectionFactory tranConnectionFactory(
            @Value("${spring.rabbitmq.tran.host}") String host,
            @Value("${spring.rabbitmq.tran.port}") int port,
            @Value("${spring.rabbitmq.tran.username}") String username,
            @Value("${spring.rabbitmq.tran.password}") String password,
            @Value("${spring.rabbitmq.tran.virtual-host}") String virtualHost,
            @Value("${spring.rabbitmq.tran.publisher-returns}")boolean confirms,
            @Value("${spring.rabbitmq.tran.publisher-confirms}")boolean returns,
            @Value("${spring.rabbitmq.tran.cache.channel.size}")int cacheSize){
        return createConnectionFactory(host,port,username,password,virtualHost,confirms,returns,cacheSize);
    }

    @Bean(name="sendConnectionFactory")
    public ConnectionFactory snedConnectionFactory(
            @Value("${spring.rabbitmq.send.host}") String host,
            @Value("${spring.rabbitmq.send.port}") int port,
            @Value("${spring.rabbitmq.send.username}") String username,
            @Value("${spring.rabbitmq.send.password}") String password,
            @Value("${spring.rabbitmq.send.virtual-host}") String virtualHost,
            @Value("${spring.rabbitmq.send.publisher-returns}")boolean confirms,
            @Value("${spring.rabbitmq.send.publisher-confirms}")boolean returns,
            @Value("${spring.rabbitmq.send.cache.channel.size}")int cacheSize){
        return createConnectionFactory(host,port,username,password,virtualHost,confirms,returns,cacheSize);
    }


    @Bean("rabbitListenerContainerFactory")
    @Primary
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("cachingConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean("receiveContainerFactory")
    public SimpleRabbitListenerContainerFactory receiveContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("receiveConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean("sendContainerFactory")
    public SimpleRabbitListenerContainerFactory sendContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("sendConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean("tranContainerFactory")
    public SimpleRabbitListenerContainerFactory tranContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("tranConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setChannelTransacted(true);
        return factory;
    }

    @Bean("sendRabbitTemplate")
    public RabbitTemplate sendRabbitTemplate(@Qualifier("sendConnectionFactory") ConnectionFactory connectionFactory) {
        return  new RabbitTemplate(connectionFactory);
    }

    @Bean("tranRabbitTemplate")
    public RabbitTemplate tranRabbitTemplate(@Qualifier("tranConnectionFactory") ConnectionFactory connectionFactory) {
        return  new RabbitTemplate(connectionFactory);
    }

    @Bean("rabbitTemplate")
    @Primary
    public RabbitTemplate rabbitTemplate(@Qualifier("cachingConnectionFactory") ConnectionFactory connectionFactory) {
        return  new RabbitTemplate(connectionFactory);
    }
}
