package com.assesment.campaigns.service;

import com.assesment.campaigns.domain.User;
import com.assesment.campaigns.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUser(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
