package com.example.architecture.hexagonal.domain.handler.unpublish;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UnpublishCommandHandler {
    ContentRepository contentRepository;

    public void unpublishContent(UnpublishCommand unpublishCommand) {
        Optional<Content> content = contentRepository.getById(unpublishCommand.id());
        content.ifPresent(Content::unpublish);
    }
}
