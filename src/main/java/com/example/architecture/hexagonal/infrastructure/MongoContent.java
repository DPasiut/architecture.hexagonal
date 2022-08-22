package com.example.architecture.hexagonal.infrastructure;

import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@EqualsAndHashCode
@SuperBuilder
@RequiredArgsConstructor
public class MongoContent {
    @Id
    String id;
    Title title;
    PageDate pageDate;
    PublishStatus publishStatus;
    PublishDate publishDate;
    Slug slug;
}
