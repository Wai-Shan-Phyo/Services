package Audit.demo.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

     public static final String QUEUE =
             "audit.queue"; //ပုံမှန် အလုပ်လုပ်မယ့် Queue

     public static final String EXCHANGE =
             "user.exchange"; //Message  Entry Point Message တွေကို လက်ခံမယ့် Exchange


     public static final String ROUTING_KEY =
             "user.created"; //Message address label Message လမ်းကြောင်းခွဲမယ့် Key


     //DLQ
     public  static final String DLQ = "audit.dlq"; //ချို့ယွင်းချက်ရှိတဲ့ Message တွေသိမ်းမယ့် Queue
     private  static final String DLX= "audit.dlx"; //အမှားဖြစ်သွားတဲ့ Message တွေကို လွှဲပေးမယ့် Exchange

     @Bean
     public Queue queue() {

//          return new Queue(QUEUE);
          return QueueBuilder
                  .durable(QUEUE) //RabbitMQ Server ပိတ်သွားရင်တောင် audit.queue ထဲက Message တွေ ပျောက်မသွားအောင် Disk ပေါ်မှာ အသေသိမ်းခိုင်း
                  .deadLetterExchange(DLX)  // <--- အရေးကြီးဆုံးနေရာ Message ကို audit.dlx (Dead Letter Exchange) ဆီကို အလိုအလျောက် လွှဲပေးလိုက်ပါလို့ ညွှန်ကြားထားတာ
                  .build();

     }

//     @Bean
//     public TopicExchange topicExchange() {
//
//          return new TopicExchange(EXCHANGE);
//     }
     @Bean
     public DirectExchange exchange(){
          return new DirectExchange(EXCHANGE); //Direct Exchange ဆိုတာ Routing Key အတိအကျတူမှ Message ကို သက်ဆိုင်ရာ Queue ထဲ ထည့်ပေးတဲ့စနစ်ပ
     }

     @Bean
     public Binding binding(
             Queue queue,
             @Qualifier("exchange") DirectExchange directExchange
     ) {

          return BindingBuilder
                  .bind(queue)
                  .to(directExchange)
                  .with(ROUTING_KEY);
     }

     //DLQ

     @Bean
     public Queue deadLetterQueue(){
          return  new Queue(DLQ); // "audit.dlq" ကို တည်ဆောက်ခြင်း
     }

     //DLE
     @Bean
     public DirectExchange deadLetterExchange(){
          return  new DirectExchange(DLX);//"audit.dlx" ကို တည်ဆောက်ခြင်း
     }


     //DLQ Binding
    @Bean
    public Binding dlqBinding(Queue deadLetterQueue,@Qualifier("deadLetterExchange") DirectExchange deadLetterExchange){
           return BindingBuilder
                   .bind(deadLetterQueue)
                   .to(deadLetterExchange)
                   .with("");


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