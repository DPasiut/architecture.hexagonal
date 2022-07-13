package com.example.architecture.hexagonal.domain.handlers.create;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.ContentDto;
import com.example.architecture.hexagonal.domain.valueobjects.DmsId;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateCommandHandler {
    private final ContentRepository contentRepository;

    public void createContent(CreateCommand createCommand) {
        contentRepository.save(buildContent(createCommand.contentDto()));
    }

    private Content buildContent(ContentDto contentDto) {
        return Content.builder()
                .dmsId(generateDmsId())
                .title(contentDto.getTitle())
                .pageDate(contentDto.getPageDate())
                .publishDate(contentDto.getPublishDate())
                .publishStatus(PublishStatus.DRAFT)
                .build();
    }
    private DmsId generateDmsId(){
        return new DmsId(UUID.randomUUID().toString());
    }
}

