package com.example.architecture.hexagonal.domain.handlers.create;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import com.example.architecture.hexagonal.domain.service.DmsIdService;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.ContentDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCommandHandler {
    private final ContentRepository contentRepository;
    private final DmsIdService dmsIdService;

    public void createContent(CreateCommand createCommand) {
        contentRepository.save(buildContent(createCommand.contentDto()));
    }

    private Content buildContent(ContentDto contentDto) {
        return Content.builder()
                .dmsId(dmsIdService.generateId())
                .title(contentDto.getTitle())
                .pageDate(contentDto.getPageDate())
                .publishDate(contentDto.getPublishDate())
                .publishStatus(PublishStatus.DRAFT)
                .build();
    }
}

