package com.example.architecture.hexagonal.domain.handlers.unpublish;

import com.example.architecture.hexagonal.domain.valueobjects.DmsId;

public record UnpublishCommand(DmsId id) {
}
