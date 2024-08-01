package com.first.first.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.first.first.Repository.FirstRepository;
import com.first.first.entity.FirstEntity;

@SpringBootTest
public class FisrtRepositoryTest {
    
    @Autowired
    private FirstRepository firstRepository;

    @Test
    void TestFirstInsert() {
        FirstEntity entity = new FirstEntity();
        entity.setName("test1");
        entity.setEmail("test@test2.com");

        FirstEntity savedEntity = firstRepository.save(entity);
        Assertions.assertNotNull(savedEntity);
        Assertions.assertEquals("test1", savedEntity.getName());
    }

}
