package com.example.architecture.hexagonal.domain.entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PACKAGE)
enum PublishStatus {
    DRAFT,
    PUBLISHED,
    UNPUBLISHED
}
