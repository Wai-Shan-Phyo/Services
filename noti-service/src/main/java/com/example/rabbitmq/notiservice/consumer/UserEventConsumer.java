package com.example.rabbitmq.notiservice.consumer;

import com.example.rabbitmq.notiservice.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventConsumer {
    @RabbitListener(
            queues = RabbitMQConfig.QUEUE
    )
    public void consume(String message){
        System.out.println(
                "Notification Received : " + message
        );

        System.out.println(
                "Sending Email..."
        );
    }
}
