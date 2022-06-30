package com.example.architecture.hexagonal.domain.exceptions;

import com.example.architecture.hexagonal.domain.valueobjects.DmsId;

public class PublishContentException extends RuntimeException {
    public PublishContentException(DmsId id) {
        super(String.format("Is not possible to publish content with id %s", id.value()));
    }
}
