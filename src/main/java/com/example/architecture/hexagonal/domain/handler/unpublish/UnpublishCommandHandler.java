package com.example.architecture.hexagonal.domain.handler.unpublish;

import com.example.architecture.hexagonal.domain.exceptions.ContentNotExistException;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UnpublishCommandHandler {
    private final ContentRepository contentRepository;

    public void unpublishContent(UnpublishCommand unpublishCommand) {
        contentRepository.getById(unpublishCommand.id()).ifPresentOrElse(content1 -> {
            content1.unpublish();
            contentRepository.save(content1);
        }, () -> {
            throw new ContentNotExistException();
        });
    }
}
