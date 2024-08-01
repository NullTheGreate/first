package com.first.first.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.first.first.entity.SecondEntity;

public interface SecondRepository extends JpaRepository<SecondEntity, Integer>{

    SecondEntity findByName(String name);
}
