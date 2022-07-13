package com.example.architecture.hexagonal.domain.handlers.delete;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.exceptions.ContentNotExistException;
import com.example.architecture.hexagonal.domain.exceptions.NotAllowedDeletePublishedContent;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteCommandHandler {
    private final ContentRepository contentRepository;

    public void deleteContent(DeleteCommand deleteCommand) {
        contentRepository.getById(deleteCommand.id()).ifPresentOrElse(content -> {
            if (canDelete(content)) {
                contentRepository.delete(content.getDmsId());
            } else {
                throw new NotAllowedDeletePublishedContent(content.getDmsId());
            }
        }, () -> {
            throw new ContentNotExistException();
        });
    }

    private boolean canDelete(Content content) {
        return content.getPublishStatus() != PublishStatus.PUBLISHED;
    }
}
