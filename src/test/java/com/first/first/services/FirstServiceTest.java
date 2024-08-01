package com.first.first.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.first.first.Repository.FirstRepository;
import com.first.first.entity.FirstEntity;
import com.first.first.service.FirstServciceImpl;
import com.first.first.service.FirstService;

@SpringBootTest
public class FirstServiceTest {

    @Mock
    private FirstService firstService;

    @Mock
    private FirstRepository firstRepository;
    
    @BeforeEach
    void setupService() {
        firstRepository = mock(FirstRepository.class);
        firstService = new FirstServciceImpl(firstRepository);
    }

    @Test
    void testFindAll() {
        FirstEntity entity = new FirstEntity();
        entity.setName("test1");
        entity.setEmail("test@test2.com");

        List<FirstEntity> list = List.of(entity);

        when(firstService.getAll()).thenReturn(list);

        assertEquals(list, firstService.getAll());
    }

}
