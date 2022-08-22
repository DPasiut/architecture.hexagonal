package com.example.architecture.hexagonal.domain.handlers.delete;

import com.example.architecture.hexagonal.domain.valueobjects.DmsId;

public record DeleteCommand(DmsId id) {
}
