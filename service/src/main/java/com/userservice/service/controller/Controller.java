package com.userservice.service.controller;

import com.userservice.service.dto.UserCreatedEvent;
import com.userservice.service.publisher.UserEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class Controller {

    private final UserEventPublisher publisher;
    public Controller(UserEventPublisher publisher){
        this.publisher=publisher;
    }

    @GetMapping("/profile")
    public String profile() {
        return "Profile Data";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin";
    }

    @GetMapping("/customer")
    public String customer() {
        return "Hello Customer";
    }

 @PostMapping("/create")
 public String create(){
     UserCreatedEvent userCreatedEvent = new UserCreatedEvent(
             UUID.randomUUID().toString(),
             1,
             "admin",
             "admin@gmail.com",
             "ADMIN"
     );

        publisher.publishUserCreated(
               userCreatedEvent
        );
        return "User Created";
 }
}
