package com.userservice.service.config;

import org.jspecify.annotations.Nullable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public  static  final String EXCHANGE = "user.exchange";
    public static final String ROUTING_KEY =
            "user.created";


//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE_NAME);
//    }

  @Bean
  public TopicExchange exchange(){
      return  new TopicExchange(EXCHANGE);
  }


    @Bean
    public JacksonJsonMessageConverter
    jsonMessageConverter() {
       return new JacksonJsonMessageConverter();
    }
}