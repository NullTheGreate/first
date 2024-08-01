package com.first.first.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.first.first.Dto.FirstDto;
import com.first.first.entity.FirstEntity;
import com.first.first.service.FirstService;

@SpringBootTest
public class FirstControllerTests {
 
    @Mock
    private Firstcontroller firstController;

    @Mock
    private FirstService firstService;

    @BeforeEach
    public void setupController() {
        firstService = mock(FirstService.class);
        firstController = new Firstcontroller(firstService);
    }

    @Test
    public void testGetAll() {
        FirstEntity entity1 = new FirstEntity();
        FirstEntity entity2 = new FirstEntity();

        entity1.setName("test1");
        entity2.setName("test2");
        entity1.setId(1);
        entity1.setEmail("test1@test.com");
        entity2.setEmail("XXXXXXXXXXXXXX");
        entity2.setId(1);

        List<FirstEntity> entities = List.of(entity1, entity2);

        when(firstController.getAll()).thenReturn(entities);

        assertEquals(entity2.getName(), firstController.getAll().get(1).getName());

    }
    
    @Test
    public void testSave() {
        FirstEntity entity = new FirstEntity();
        entity.setName("test");
        entity.setEmail("XXXXXXXXXXXXX");
        entity.setId(1);

        FirstDto firstDto = new FirstDto("test", "XXXXXXXXXXXXX");

        when(firstController.saveFirst(firstDto)).thenReturn(entity);

        assertEquals(entity.getName(), firstController.saveFirst(firstDto).getName());

    }

}
