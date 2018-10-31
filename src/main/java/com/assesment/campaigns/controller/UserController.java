package com.assesment.campaigns.controller;

import com.assesment.campaigns.domain.User;
import com.assesment.campaigns.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/user/login")
    private boolean getUser(@RequestBody User user) {
        return userService.getUser(user) != null;
    }

    @CrossOrigin
    @PostMapping("/user/signup")
    private boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user) != null;
    }
}
