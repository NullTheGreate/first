package com.first.first.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.first.first.Dto.FirstDto;
import com.first.first.entity.FirstEntity;

@Service
public interface FirstService {

    public String firstMethod();

    public List<FirstEntity> getAll();

    public FirstEntity saveFirst(FirstDto firstDto);
}
