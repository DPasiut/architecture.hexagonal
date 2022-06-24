package com.example.architecture.hexagonal.domain.entity.types;

public enum PublishStatus {
    DRAFT("DRAFT"),
    PUBLISHED("PUBLISHED"),
    UNPUBLISHED("UNPUBLISHED");

    private String value;
    PublishStatus(String value) {
        this.value = value;
    }
}
