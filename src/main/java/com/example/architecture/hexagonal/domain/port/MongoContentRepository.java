package com.example.architecture.hexagonal.domain.port;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.valueobjects.DmsId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoContentRepository extends MongoRepository<Content, DmsId> {
}
