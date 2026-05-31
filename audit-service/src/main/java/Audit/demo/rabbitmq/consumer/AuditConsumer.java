package Audit.demo.rabbitmq.consumer;

import Audit.demo.rabbitmq.config.RabbitMQConfig;
import Audit.demo.rabbitmq.dto.UserCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AuditConsumer {
    @RabbitListener(
            queues = RabbitMQConfig.QUEUE
    )
    public void consume(UserCreatedEvent event){
        System.out.println(
                "AUDIT EVENT RECEIVED"
        );

        System.out.println(
                "User : " + event.getName()
        );

        System.out.println(
                "Role : " + event.getRole()
        );

        System.out.println(
                "Email : " + event.getEmail()
        );
        System.out.println(
                "PROCESSING EVENT"
        );

        throw new RuntimeException(
                "SIMULATED FAILURE"
        );
    }
}
