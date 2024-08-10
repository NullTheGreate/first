package com.first.first.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.first.first.Repository.UserRepository;
import com.first.first.entity.User;

@Service
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

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserDetails findByUsernameWithRoles(String username) {
        User user = userRepository.UserfindByUsernameWithRoles(username);
        UserDetails userDetails = new UserDetails() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList());
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return user.isAccountNonExpired();
            }

            @Override
            public boolean isAccountNonLocked() {
                return user.isAccountNonLocked();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return user.isCredentialsNonExpired();
            }

            @Override
            public boolean isEnabled() {
                return user.isEnabled();
            }
        };
        return userDetails;
    }
}
