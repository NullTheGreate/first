package com.first.first.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.first.first.Dto.FirstDto;
import com.first.first.Repository.FirstRepository;
import com.first.first.entity.FirstEntity;

@Service
public class FirstServciceImpl implements FirstService {

    private FirstRepository firstRepository;

    public FirstServciceImpl(FirstRepository firstRepository) {
        this.firstRepository = firstRepository;
    }

    @Override
    public String firstMethod() {
        return "Hello";
    }

    @Override
    public List<FirstEntity> getAll() {
        return this.firstRepository.findAll();
    }

    @Override
    public FirstEntity saveFirst(FirstDto firstDto) {
        FirstEntity firstEntity = new FirstEntity();
        firstEntity.setName(firstDto.name());
        firstEntity.setEmail(firstDto.email());
        return this.firstRepository.save(firstEntity);
    }
    
}
