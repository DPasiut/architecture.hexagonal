package com.example.architecture.hexagonal.domain.valueobjects;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContentDto {
    Title title;
    PageDate pageDate;
    PublishDate publishDate;
}
