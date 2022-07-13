package com.example.architecture.hexagonal.domain.handlers.create;

import com.example.architecture.hexagonal.domain.valueobjects.ContentDto;

public record CreateCommand(ContentDto contentDto) {
}
