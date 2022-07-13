package com.example.architecture.hexagonal.domain.handlers;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.exceptions.ContentNotExistException;
import com.example.architecture.hexagonal.domain.exceptions.NotAllowedDeletePublishedContent;
import com.example.architecture.hexagonal.domain.exceptions.TooLongTitleException;
import com.example.architecture.hexagonal.domain.handlers.delete.DeleteCommand;
import com.example.architecture.hexagonal.domain.handlers.delete.DeleteCommandHandler;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.DmsId;
import com.example.architecture.hexagonal.domain.valueobjects.PageDate;
import com.example.architecture.hexagonal.domain.valueobjects.Title;
import com.example.architecture.hexagonal.infrastructure.InMemoryContentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

@Test(testName = "Deleting Content")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteContentTest {
    InMemoryContentRepository inMemoryContentRepository = new InMemoryContentRepository();
    final DeleteCommandHandler deleteCommandHandler = new DeleteCommandHandler(inMemoryContentRepository);
    final DmsId ID = new DmsId("newId");

    @Test
    public void shouldDeleteContentWithStatusOtherThanPublished() {
        Content content = Content.builder()
                .dmsId(ID)
                .title(new Title("title"))
                .pageDate(new PageDate(LocalDate.now()))
                .publishStatus(PublishStatus.DRAFT)
                .build();

        inMemoryContentRepository.save(content);
        deleteCommandHandler.deleteContent(new DeleteCommand(ID));
        assertEquals(inMemoryContentRepository.getById(ID), Optional.empty());
    }

    @Test
    public void shouldNotDeleteContentWithStatusEqualsPublished() {
        Content content = Content.builder()
                .dmsId(ID)
                .title(new Title("title"))
                .pageDate(new PageDate(LocalDate.now()))
                .publishStatus(PublishStatus.PUBLISHED)
                .build();
        inMemoryContentRepository.save(content);
        assertThrows(NotAllowedDeletePublishedContent.class, () -> {
            deleteCommandHandler.deleteContent(new DeleteCommand(ID));
        });


    }
}
