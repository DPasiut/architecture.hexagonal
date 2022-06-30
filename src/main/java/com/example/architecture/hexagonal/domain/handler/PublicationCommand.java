package com.example.architecture.hexagonal.domain.handler;

import com.example.architecture.hexagonal.domain.valueobjects.DmsId;

public record PublicationCommand(DmsId id) {
}
