package com.example.architecture.hexagonal.domain.handler.publish;

import com.example.architecture.hexagonal.domain.exceptions.ContentNotExistException;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PublishCommandHandler {
    private final ContentRepository contentRepository;

    public void publishContent(PublishCommand publishCommand) {
        contentRepository.getById(publishCommand.id()).ifPresentOrElse(content1 -> {
            content1.publish();
            contentRepository.save(content1);
        }, () -> {
            throw new ContentNotExistException();
        });
    }
}
