package com.example.architecture.hexagonal.application.configuration;

import com.example.architecture.hexagonal.domain.handler.PublicationCommandHandler;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public PublicationCommandHandler publicationCommandHandler(ContentRepository contentRepository) {
        return new PublicationCommandHandler(contentRepository);
    }

}
