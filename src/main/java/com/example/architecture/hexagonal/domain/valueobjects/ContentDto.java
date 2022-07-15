package com.example.architecture.hexagonal.domain.valueobjects;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContentDto {
    String title;
    String pageDate;
    String publishDate;
}
