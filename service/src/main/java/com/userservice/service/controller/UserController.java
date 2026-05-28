package com.userservice.service.controller;

import com.userservice.service.database.UserEntity;
import com.userservice.service.database.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usertest")
public class UserController {
    private final UserRepository userRepository;
    public  UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @PostMapping("/create")
    public String createuser(){
        UserEntity user = new UserEntity();
        user.setName("admin");

        user.setEmail("admin@gmail.com");

        user.setRole("ADMIN");

        userRepository.save(user);

        return "USER SAVED";
    }

}
