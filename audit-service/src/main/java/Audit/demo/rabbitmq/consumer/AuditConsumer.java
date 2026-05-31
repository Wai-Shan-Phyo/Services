package Audit.demo.rabbitmq.consumer;

import Audit.demo.rabbitmq.config.RabbitMQConfig;
import Audit.demo.rabbitmq.database.ProcessEvent;
import Audit.demo.rabbitmq.database.ProcessedEventRepository;
import Audit.demo.rabbitmq.dto.UserCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditConsumer {
    private final ProcessedEventRepository repository;


    public AuditConsumer(
            ProcessedEventRepository repository
    ) {
        this.repository = repository;
    }
    @RabbitListener(
            queues = RabbitMQConfig.QUEUE
    )
    public void consume(UserCreatedEvent event){
        if(repository.existsById(event.getEventId())){
            System.out.println(
                    "DUPLICATE EVENT SKIPPED"
            );

            return;
        }
        System.out.println(
                "AUDIT EVENT RECEIVED"
        );

        System.out.println(
                event.getName()
        );
        repository.save(new ProcessEvent(
                event.getEventId(),
                LocalDateTime.now()
        ));
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
