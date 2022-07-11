package com.example.architecture.hexagonal.application.configuration;

import com.example.architecture.hexagonal.infrastructure.MongoContentRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Profile("dev")
@EnableMongoRepositories(basePackageClasses = MongoContentRepository.class)
@Configuration
class MongoConfiguration {
}
