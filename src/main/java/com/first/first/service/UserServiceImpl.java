package com.first.first.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.first.first.Repository.UserRepository;
import com.first.first.entity.User;

public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByName(String name) {
        User user;
        try {
            user = userRepository.findByUsername(name).orElseThrow(() -> new Exception());
            return user;
        } catch (Exception e) {
        
            e.printStackTrace();
            
        }
        
        return null;
    }
    
}
