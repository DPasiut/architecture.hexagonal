package com.example.architecture.hexagonal.domain.exceptions;

import com.example.architecture.hexagonal.domain.valueobjects.DmsId;

public class ContentNotFoundException extends RuntimeException {
    public ContentNotFoundException(DmsId id) {
        super(String.format("Content with id %s not found", id.value()));
    }
}
