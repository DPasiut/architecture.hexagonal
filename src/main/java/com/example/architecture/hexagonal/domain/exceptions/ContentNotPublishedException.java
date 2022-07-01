package com.example.architecture.hexagonal.domain.exceptions;

import com.example.architecture.hexagonal.domain.valueobjects.DmsId;

public class ContentNotPublishedException extends RuntimeException {
    public ContentNotPublishedException(DmsId id) {
        super(String.format("Is not possible to unpublish content with id %s", id.value()));
    }
}
