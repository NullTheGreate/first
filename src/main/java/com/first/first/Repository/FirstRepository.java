package com.first.first.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.first.first.entity.FirstEntity;

@Repository
public interface FirstRepository extends JpaRepository<FirstEntity, Integer> {
    
}
