package com.first.first.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
public class FirstControllerMockito {
    
    @InjectMocks
    private Firstcontroller firstController;

    private static MockMvc mockMvc;

    @BeforeAll()
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(firstController).build();
    }

    @Test
    void testFirstMethod() throws Exception {
        mockMvc.perform(get("/first"))
        .andExpect(result -> result.getResponse().getContentAsString().contains("Hello"));
    }
}
