package com.first.first.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import com.first.first.entity.User;

// @Service
public interface UserService {
    
    User findByName(String name);

    public List<User> findAll();

    public UserDetails findByUsernameWithRoles(String username);
}
