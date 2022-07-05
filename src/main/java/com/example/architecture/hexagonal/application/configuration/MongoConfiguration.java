package com.example.architecture.hexagonal.application.configuration;

import com.example.architecture.hexagonal.domain.port.MongoContentRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = MongoContentRepository.class)
@Configuration
class MongoConfiguration {
}
