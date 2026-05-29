package com.userservice.service.publisher;

import com.userservice.service.config.RabbitMQConfig;
import com.userservice.service.database.OutboxEvent;
import com.userservice.service.database.OutboxEventRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutBoxPublisher {
    private final OutboxEventRepository outboxEventRepository;
    private final RabbitTemplate rabbitTemplate;
    public OutBoxPublisher(OutboxEventRepository outboxEventRepository, RabbitTemplate rabbitTemplate){
         this.outboxEventRepository=outboxEventRepository;
         this.rabbitTemplate=rabbitTemplate;
    }
    @Scheduled(fixedRate = 5000)
    public void publishPendingEvent(){
        List<OutboxEvent> events = outboxEventRepository.findByStatus("PENDING");
         for (OutboxEvent event : events ){
             try {
                 rabbitTemplate.convertAndSend(
                         RabbitMQConfig.EXCHANGE,
                         RabbitMQConfig.ROUTING_KEY,
                         event.getPayload()
                 );
                 event.setStatus("SENT");
                 outboxEventRepository.save(event);
                 System.out.print(
                         "Event Published" + event.getId()
                 );

             }catch (Exception e){
                 System.out.print("Publish Failed");
                 event.setStatus("FAILED");
                 outboxEventRepository.save(event);
             }
         }
    }
}
