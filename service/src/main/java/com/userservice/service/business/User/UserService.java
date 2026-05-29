package com.userservice.service.business.User;

import com.userservice.service.database.OutboxEvent;
import com.userservice.service.database.OutboxEventRepository;
import com.userservice.service.database.UserEntity;
import com.userservice.service.database.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final OutboxEventRepository outboxEventRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
       public UserService(OutboxEventRepository outboxEventRepository, UserRepository userRepository, ObjectMapper objectMapper){
           this.outboxEventRepository=outboxEventRepository;
           this.userRepository=userRepository;
           this.objectMapper=objectMapper;
       }

       @Transactional
       public void create() throws Exception{
           UserEntity user = new UserEntity();
           user.setName("wai");
           user.setEmail("hahawah538@gmail.com");
           user.setRole("ADMIN");
           userRepository.save(user);
           String payload = objectMapper.writeValueAsString(user); //change object to Json FORMAT

           OutboxEvent event = new OutboxEvent();
           event.setEvent_type("USER.CREATED");
           event.setPayload(payload);
           event.setStatus("PENDING");
           event.setLocalDateTime(LocalDateTime.now());
           outboxEventRepository.save(event);
           System.out.println(
                   "USER + OUTBOX SAVED"
           );
       }
}
