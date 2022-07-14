package com.example.architecture.hexagonal.domain.service;

import com.example.architecture.hexagonal.domain.valueobjects.DmsId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DmsIdService {
    public DmsId generateId(){
        return new DmsId(UUID.randomUUID().toString());
    }
}
