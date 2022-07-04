package com.example.architecture.hexagonal.domain.handlers.publish;

import com.example.architecture.hexagonal.domain.exceptions.ContentNotExistException;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PublishCommandHandler {
    private final ContentRepository contentRepository;

    public void publishContent(PublishCommand publishCommand) {
        contentRepository.getById(publishCommand.id()).ifPresentOrElse(content -> {
            content.publish();
            contentRepository.save(content);
        }, () -> {
            throw new ContentNotExistException();
        });
    }
}
