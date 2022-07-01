package com.example.architecture.hexagonal.domain.handler.publish;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class PublishCommandHandler {
    ContentRepository contentRepository;

    public void publishContent(PublishCommand publishCommand) {
        Optional<Content> content = contentRepository.getById(publishCommand.id());
        content.ifPresent(Content::publish);
    }
}
