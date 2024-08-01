package com.first.first.service;

import java.lang.management.MemoryMXBean;

import org.springframework.stereotype.Service;

import com.first.first.Dto.SecondDto;
import com.first.first.entity.SecondEntity;

@Service
public interface SecondService {
    
    public String secondMethod(MemoryMXBean memoryBean);

    public SecondEntity addSecond(SecondDto secondDto);

    public SecondEntity updateSecondEntity(SecondDto secondDto);
}
