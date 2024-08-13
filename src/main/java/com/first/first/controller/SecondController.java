package com.first.first.controller;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.first.first.Dto.SecondDto;
import com.first.first.Exception.UserNotFoundException;
import com.first.first.entity.SecondEntity;
import com.first.first.service.SecondService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/second")
public class SecondController {
    
    @Autowired
    private SecondService secondService;

    public SecondController(SecondService secondService) {
        this.secondService = secondService;
    }

    @GetMapping("/getSecond")
    public String getSecond() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        return secondService.secondMethod(memoryBean);
    }

    @PostMapping("/")   
    public SecondEntity saveSecond(@RequestBody SecondDto secondDto) {
        return secondService.addSecond(secondDto);
    }

    @PutMapping("/")
    public SecondEntity updateSecond(@RequestBody SecondDto secondDto) {
        return secondService.updateSecondEntity(secondDto);
    }

    @GetMapping("/getSecondStr")
    public String getSecondStr() {
        throw new UserNotFoundException("User not found");
    }

}
