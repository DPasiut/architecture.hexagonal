package com.example.architecture.hexagonal.application.rest;

import com.example.architecture.hexagonal.domain.handlers.publish.PublishCommand;
import com.example.architecture.hexagonal.domain.handlers.publish.PublishCommandHandler;
import com.example.architecture.hexagonal.domain.handlers.unpublish.UnpublishCommand;
import com.example.architecture.hexagonal.domain.handlers.unpublish.UnpublishCommandHandler;
import com.example.architecture.hexagonal.domain.valueobjects.DmsId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/content/{dmsId}")
public class PublishContentController {

    private final PublishCommandHandler publishCommandHandler;
    private final UnpublishCommandHandler unpublishCommandHandler;

    @PostMapping("/publish")
    public ResponseEntity<Void> publishContent(@PathVariable String dmsId) {
        PublishCommand publishCommand = new PublishCommand(new DmsId(dmsId));
        publishCommandHandler.publishContent(publishCommand);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unpublish")
    public ResponseEntity<Void> unpublishContent(@PathVariable String dmsId) {
        UnpublishCommand unpublishCommand = new UnpublishCommand(new DmsId(dmsId));
        unpublishCommandHandler.unpublishContent(unpublishCommand);
        return ResponseEntity.ok().build();
    }
}
