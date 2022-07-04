package com.example.architecture.hexagonal.domain.handlers.publish;

import com.example.architecture.hexagonal.domain.valueobjects.DmsId;

public record PublishCommand(DmsId id) {
}
