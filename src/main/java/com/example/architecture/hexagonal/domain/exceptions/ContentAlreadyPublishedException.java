package com.example.architecture.hexagonal.domain.exceptions;

import com.example.architecture.hexagonal.domain.valueobjects.DmsId;

public class ContentAlreadyPublishedException extends RuntimeException {
    public ContentAlreadyPublishedException(DmsId id) {
        super(String.format("Is not possible to publish content with id %s", id.value()));
    }
}
