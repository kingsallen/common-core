package com.moseeker.config;/**
 * Created by zztaiwll on 18/11/29.
 */

import com.moseeker.configuration.AfterTraceMessagePostProcessor;
import com.moseeker.configuration.BeforeTraceMessagePostProcessor;
import com.moseeker.constant.LogType;
import com.moseeker.util.LogUtil;
import com.rabbitmq.client.ConnectionFactory;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @version 1.0
 * @className ConfigRabbitMQ
 * @Description TODO
 * @Author zztaiwll
 * @DATE 18/11/29 下午3:27
 **/
@Configuration
@EnableRabbit
public class ConfigRabbitMQ {
    @Autowired
    private Environment env;
    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost(env.getProperty("spring.rabbitmq.host").trim());
        cf.setPort(Integer.valueOf(env.getProperty("spring.rabbitmq.port").trim()));
        cf.setUsername(env.getProperty("spring.rabbitmq.username").trim());
        cf.setPassword(env.getProperty("spring.rabbitmq.password").trim());
        return cf;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory());
        RetryTemplate retryTemplate = new RetryTemplate();
        // 重试机制
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(500);
        backOffPolicy.setMultiplier(10.0);
        backOffPolicy.setMaxInterval(10000);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        rabbitTemplate.setRetryTemplate(retryTemplate);
        rabbitTemplate.setBeforePublishPostProcessors(beforeTraceMessagePostProcessor());
        //rabbitTemplate.setAfterReceivePostProcessors(afterTraceMessagePostProcessor());
        return rabbitTemplate;
    }

    @Bean
   public BeforeTraceMessagePostProcessor beforeTraceMessagePostProcessor(){
        return new BeforeTraceMessagePostProcessor();
    }

//    @Bean
//    public AfterTraceMessagePostProcessor afterTraceMessagePostProcessor(){
//        return new AfterTraceMessagePostProcessor();
//    }


//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer(){
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(cachingConnectionFactory());
//        container.setQueueNames("TestQue");
//        container.setAfterReceivePostProcessors(afterTraceMessagePostProcessor());
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        container.setMessageListener((MessageListener) message -> {
//            LogUtil.CommonLog(LogType.Info,new String(message.getBody()));
//        });
//        return container;
//    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        return new CachingConnectionFactory(connectionFactory());
    }


    @Bean
    public AmqpAdmin amqpAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(cachingConnectionFactory());
        rabbitAdmin.setIgnoreDeclarationExceptions(true);
        return rabbitAdmin;
    }

    /**
     * listener 容器 （consumer 需要手动确认消息）
     * @return
     */
    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory listenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        listenerContainerFactory.setConnectionFactory(cachingConnectionFactory());
        // 设置手动 ACK
        listenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return listenerContainerFactory;
    }

    /**
     * listener 容器 （AcknowledgeMode：auto）
     * @return
     */
    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactoryAutoAck() {
        SimpleRabbitListenerContainerFactory listenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        listenerContainerFactory.setConnectionFactory(cachingConnectionFactory());
        return listenerContainerFactory;
    }
}
