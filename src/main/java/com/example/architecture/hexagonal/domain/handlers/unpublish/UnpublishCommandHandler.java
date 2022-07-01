package com.example.architecture.hexagonal.domain.handlers.unpublish;

import com.example.architecture.hexagonal.domain.exceptions.ContentNotExistException;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UnpublishCommandHandler {
    private final ContentRepository contentRepository;

    public void unpublishContent(UnpublishCommand unpublishCommand) {
        contentRepository.getById(unpublishCommand.id()).ifPresentOrElse(content -> {
            content.unpublish();
            contentRepository.save(content);
        }, () -> {
            throw new ContentNotExistException();
        });
    }
}
