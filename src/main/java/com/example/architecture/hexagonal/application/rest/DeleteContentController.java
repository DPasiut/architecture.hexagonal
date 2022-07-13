package com.example.architecture.hexagonal.application.rest;

import com.example.architecture.hexagonal.domain.handlers.delete.DeleteCommand;
import com.example.architecture.hexagonal.domain.handlers.delete.DeleteCommandHandler;
import com.example.architecture.hexagonal.domain.valueobjects.DmsId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/content/{dmsId}")
public class DeleteContentController {
    private final DeleteCommandHandler deleteCommandHandler;

    @DeleteMapping
    public ResponseEntity<Void> deleteContent(@PathVariable String dmsId) {
        DeleteCommand deleteCommand = new DeleteCommand(new DmsId(dmsId));
        deleteCommandHandler.deleteContent(deleteCommand);
        return ResponseEntity.ok().build();
    }
}
