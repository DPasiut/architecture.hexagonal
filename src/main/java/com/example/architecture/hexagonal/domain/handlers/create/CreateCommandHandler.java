package com.example.architecture.hexagonal.domain.handlers.create;

import com.example.architecture.hexagonal.domain.port.ContentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCommandHandler {
    private final ContentRepository contentRepository;

    public void createContent(CreateCommand createCommand) {
        contentRepository.save(createCommand.content());
    }
}

