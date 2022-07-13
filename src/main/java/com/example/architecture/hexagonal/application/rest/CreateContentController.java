package com.example.architecture.hexagonal.application.rest;

import com.example.architecture.hexagonal.domain.handlers.create.CreateCommand;
import com.example.architecture.hexagonal.domain.handlers.create.CreateCommandHandler;
import com.example.architecture.hexagonal.domain.valueobjects.ContentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/content")
public class CreateContentController {
    private final CreateCommandHandler createCommandHandler;

    @PutMapping("/")
    public ResponseEntity<Void> createContent(@RequestBody ContentDto contentDto) {
        createCommandHandler.createContent(new CreateCommand(contentDto.toContent()));
        return ResponseEntity.ok().build();
    }
}
