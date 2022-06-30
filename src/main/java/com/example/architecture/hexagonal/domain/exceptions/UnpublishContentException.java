package com.example.architecture.hexagonal.domain.exceptions;

import com.example.architecture.hexagonal.domain.valueobjects.DmsId;

public class UnpublishContentException extends RuntimeException {
    public UnpublishContentException(DmsId id) {
        super(String.format("Is not possible to unpublish content with id %s", id.value()));
    }
}
