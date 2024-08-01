package com.first.first.service;

import java.lang.management.MemoryMXBean;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.first.Dto.SecondDto;
import com.first.first.Repository.SecondRepository;
import com.first.first.entity.SecondEntity;
import com.first.first.thread.ThreadLogic;

@Service
public class SecondServiceImpl implements SecondService {
    
    @Autowired
    private SecondRepository secondRepository;

    @Override
    public String secondMethod(MemoryMXBean memoryBean) {

        List<Integer> list = List.of(1, 2, 3, 4, 5);

        ThreadLogic logic = new ThreadLogic(list, memoryBean);
        Thread thread = new Thread(logic);
        thread.start();
        return "Hello Second";
    }

    @Override
    public SecondEntity addSecond(SecondDto secondDto) {
        SecondEntity secondEntity = new SecondEntity();
        secondEntity.setName(secondDto.getName());
        secondEntity.setPopilation(secondDto.getPopulation());
        return secondRepository.save(secondEntity);
    }

    @Override
    public SecondEntity updateSecondEntity(SecondDto secondDto) {
        SecondEntity secondEntity = secondRepository.findByName(secondDto.getName());
        secondEntity.setPopilation(secondDto.getPopulation());

        return secondRepository.save(secondEntity);
    }
    
}
