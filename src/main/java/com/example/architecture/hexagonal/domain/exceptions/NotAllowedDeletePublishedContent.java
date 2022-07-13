package com.example.architecture.hexagonal.domain.exceptions;

import com.example.architecture.hexagonal.domain.valueobjects.DmsId;

public class NotAllowedDeletePublishedContent extends RuntimeException {
    public NotAllowedDeletePublishedContent(DmsId dmsId) {
        super(String.format("Not allowed to delete already published content with id [%s]", dmsId.value()));
    }
}
