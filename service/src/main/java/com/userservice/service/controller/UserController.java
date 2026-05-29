package com.userservice.service.controller;

import com.userservice.service.business.User.UserService;
import com.userservice.service.database.UserEntity;
import com.userservice.service.database.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usertest")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    public  UserController(UserRepository userRepository,UserService userService){
        this.userRepository=userRepository;
        this.userService=userService;
    }
    @PostMapping("/create")
    public String createuser() throws Exception{
         userService.create();
         return "User Created";
    }

}
