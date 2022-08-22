package com.example.architecture.hexagonal.application.configuration;

import com.example.architecture.hexagonal.domain.handlers.create.CreateCommandHandler;
import com.example.architecture.hexagonal.domain.handlers.delete.DeleteCommandHandler;
import com.example.architecture.hexagonal.domain.handlers.publish.PublishCommandHandler;
import com.example.architecture.hexagonal.domain.handlers.unpublish.UnpublishCommandHandler;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import com.example.architecture.hexagonal.domain.service.DmsIdService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContentConfiguration {
    @Bean
    public PublishCommandHandler publishCommandHandler(ContentRepository contentRepository) {
        return new PublishCommandHandler(contentRepository);
    }

    @Bean
    public UnpublishCommandHandler unpublishCommandHandler(ContentRepository contentRepository) {
        return new UnpublishCommandHandler(contentRepository);
    }

    @Bean
    public CreateCommandHandler createCommandHandler(ContentRepository contentRepository, DmsIdService dmsIdService) {
        return new CreateCommandHandler(contentRepository, dmsIdService);
    }

    @Bean
    public DeleteCommandHandler deleteCommandHandler(ContentRepository contentRepository){
        return new DeleteCommandHandler(contentRepository);
    }
}
