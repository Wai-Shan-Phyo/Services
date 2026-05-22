package com.userservice.service.publisher;

import com.userservice.service.config.RabbitMQConfig;
import com.userservice.service.dto.UserCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    public UserEventPublisher(
            RabbitTemplate rabbitTemplate
    ) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void publishUserCreated(UserCreatedEvent event) {
       rabbitTemplate.convertAndSend(
               RabbitMQConfig.EXCHANGE,
               RabbitMQConfig.ROUTING_KEY,
               event
       );
        System.out.println(
                "Event Published"
        );
    }

}
