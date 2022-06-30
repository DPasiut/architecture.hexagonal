package com.example.architecture.hexagonal.application.rest;

import com.example.architecture.hexagonal.domain.handler.PublicationCommand;
import com.example.architecture.hexagonal.domain.handler.PublicationCommandHandler;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.DmsId;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "content")
public class PublicationContentController {

    private final PublicationCommandHandler publicationCommandHandler;

    @PostMapping("/publish/{dmsId}")
    public ResponseEntity<PublishStatus> publishContent(@PathVariable String dmsId) {
        PublicationCommand publicationCommand = new PublicationCommand(new DmsId(dmsId));
        PublishStatus publishStatus = publicationCommandHandler.publishContent(publicationCommand);

        return ResponseEntity.ok(publishStatus);
    }

    @PostMapping("/unpublish/{dmsId}")
    public ResponseEntity<PublishStatus> unpublishContent(@PathVariable String dmsId) {
        PublicationCommand publicationCommand = new PublicationCommand(new DmsId(dmsId));
        PublishStatus publishStatus = publicationCommandHandler.unpublishContent(publicationCommand);

        return ResponseEntity.ok(publishStatus);
    }
}
