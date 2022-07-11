package com.example.architecture.hexagonal.infrastructure;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.valueobjects.DmsId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ContentMapper {
    Optional<Content> mapToContent(Optional<MongoContent> mongoContent) {

        return mongoContent.map(mongoContent1 -> Content.builder()
                .slug(mongoContent1.slug)
                .publishDate(mongoContent1.publishDate)
                .title(mongoContent1.title)
                .pageDate(mongoContent1.pageDate)
                .dmsId(new DmsId(mongoContent1.id))
                .publishStatus(mongoContent1.publishStatus)
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