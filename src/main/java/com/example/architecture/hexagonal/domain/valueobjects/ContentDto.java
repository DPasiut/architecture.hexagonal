package com.example.architecture.hexagonal.domain.valueobjects;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ContentDto {
    Title title;
    PageDate pageDate;
    PublishDate publishDate;


    public Content toContent() {
        return Content.builder()
                .dmsId(new DmsId(UUID.randomUUID().toString()))
                .title(this.title)
                .pageDate(this.pageDate)
                .publishDate(this.publishDate)
                .publishStatus(PublishStatus.DRAFT)
                .build();
    }
}
