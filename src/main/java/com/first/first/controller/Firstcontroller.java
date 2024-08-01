package com.first.first.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.first.Dto.FirstDto;
import com.first.first.entity.FirstEntity;
import com.first.first.service.FirstService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/first")
public class Firstcontroller {

    private FirstService firstService;

    public Firstcontroller(FirstService firstService) {
        this.firstService = firstService;
    }

    @GetMapping("/hello")
    public String firstMethod() {
        System.out.println("hello");
        return "Hello";
    }

    @PostMapping("/save")       
    public FirstEntity saveFirst(@RequestBody FirstDto firstDto) {
        return this.firstService.saveFirst(firstDto);
    }

    @GetMapping("/getAll")
    public List<FirstEntity> getAll() {
        return this.firstService.getAll();
    }
    
}
