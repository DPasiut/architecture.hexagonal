package com.example.architecture.hexagonal.domain.handler;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.exceptions.PublishContentException;
import com.example.architecture.hexagonal.domain.exceptions.UnpublishContentException;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class PublicationCommandHandler {
    final ContentRepository contentRepository;

    public PublishStatus publishContent(PublicationCommand publicationCommand) {
        Optional<Content> content = contentRepository.getById(publicationCommand.id());
        if (content.isPresent()) {
            content.get().publish();
            return content.get().getPublishStatus();
        }
        throw new PublishContentException(publicationCommand.id());
    }

    public PublishStatus unpublishContent(PublicationCommand publicationCommand) {
        Optional<Content> content = contentRepository.getById(publicationCommand.id());
        if (content.isPresent()) {
            content.get().unpublish();
            return content.get().getPublishStatus();
        }
        throw new UnpublishContentException(publicationCommand.id());
    }
}
