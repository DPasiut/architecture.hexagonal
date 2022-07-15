package com.example.architecture.hexagonal.domain.handlers.create;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import com.example.architecture.hexagonal.domain.service.DmsIdService;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.ContentDto;
import com.example.architecture.hexagonal.domain.valueobjects.PageDate;
import com.example.architecture.hexagonal.domain.valueobjects.PublishDate;
import com.example.architecture.hexagonal.domain.valueobjects.Title;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

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
                .title(new Title(contentDto.getTitle()))
                .pageDate(new PageDate(LocalDate.parse(contentDto.getPageDate())))
                .publishDate(new PublishDate(LocalDate.parse(contentDto.getPublishDate())))
                .publishStatus(PublishStatus.DRAFT)
                .build();
    }
}

