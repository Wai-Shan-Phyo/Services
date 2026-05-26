package Audit.demo.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

     public static final String QUEUE =
             "audit.queue";

     public static final String ROUTING_KEY =
             "user.created"; //Message label

     public static final String EXCHANGE =
             "user.exchange"; //Message Entry

     @Bean
     public Queue queue() {

          return new Queue(QUEUE);
     }

     @Bean
     public TopicExchange topicExchange() {

          return new TopicExchange(EXCHANGE);
     }

     @Bean
     public Binding binding(
             Queue queue,
             TopicExchange topicExchange
     ) {

          return BindingBuilder
                  .bind(queue)
                  .to(topicExchange)
                  .with(ROUTING_KEY);
     }

     @Bean
     public MessageConverter messageConverter() {

          return new JacksonJsonMessageConverter();
     }

     @Bean
     public SimpleRabbitListenerContainerFactory
     rabbitListenerContainerFactory(
             ConnectionFactory connectionFactory,
             MessageConverter messageConverter
     ) {

          SimpleRabbitListenerContainerFactory factory =
                  new SimpleRabbitListenerContainerFactory();

          factory.setConnectionFactory(
                  connectionFactory
          );

          factory.setMessageConverter(
                  messageConverter
          );

          return factory;
     }
}