package com.example.architecture.hexagonal.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MongoContentRepository extends MongoRepository<MongoContent, String> {
}
