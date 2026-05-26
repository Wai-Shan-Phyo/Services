package com.example.rabbitmq.notiservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.JacksonJsonMessageConverter;

@Configuration
public class RabbitMQConfig {
//    public static final String QUEUE_NAME =
//            "user.created.queue";
//
//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE_NAME);
//    }

    public static final String QUEUE = "notification.queue";
    public static final String EXCHANGE = "user.exchange";
    private static final String ROUTING_KEY= "user.created";

    @Bean
    public Queue queue(

    ){
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public JacksonJsonMessageConverter jsonMessageConverter(){
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

}
