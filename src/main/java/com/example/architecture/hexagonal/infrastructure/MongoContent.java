package com.example.architecture.hexagonal.infrastructure;

import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

@Getter
@EqualsAndHashCode
@SuperBuilder
public class MongoContent {

    @Id
    @NonNull
    String id;
    @NonNull
    Title title;
    @NonNull
    PageDate pageDate;
    PublishStatus publishStatus;
    PublishDate publishDate;
    Slug slug;
}
