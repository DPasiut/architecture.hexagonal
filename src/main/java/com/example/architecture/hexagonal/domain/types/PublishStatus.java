package com.example.architecture.hexagonal.domain.types;

public enum PublishStatus {
    DRAFT("DRAFT"),
    PUBLISHED("PUBLISHED"),
    UNPUBLISHED("UNPUBLISHED");

    private String value;
    PublishStatus(String value) {
        this.value = value;
    }
}
