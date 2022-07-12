package com.example.architecture.hexagonal.infrastructure;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.valueobjects.DmsId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ContentMapper {
    Optional<Content> mapToContent(MongoContent mongoContent) {
        return Optional.ofNullable(Content.builder()
                .slug(mongoContent.slug)
                .publishDate(mongoContent.publishDate)
                .title(mongoContent.title)
                .pageDate(mongoContent.pageDate)
                .dmsId(new DmsId(mongoContent.id))
                .publishStatus(mongoContent.publishStatus)
                .build());

    }

    MongoContent mapToMongoContent(Content content) {
        return MongoContent.builder()
                .id(content.getDmsId().value())
                .title(content.getTitle())
                .pageDate(content.getPageDate())
                .publishStatus(content.getPublishStatus())
                .publishDate(content.getPublishDate())
                .slug(content.getSlug())
                .build();
    }
}