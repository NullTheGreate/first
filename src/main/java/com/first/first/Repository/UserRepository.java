package com.first.first.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.first.first.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name);
}
