package com.example.architecture.hexagonal.domain.handlers.publish;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.exceptions.ContentAlreadyPublishedException;
import com.example.architecture.hexagonal.domain.exceptions.ContentNotExistException;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.DmsId;
import com.example.architecture.hexagonal.domain.valueobjects.PageDate;
import com.example.architecture.hexagonal.domain.valueobjects.Title;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

@Test(testName = "Publish Content")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublishCommandHandlerTest {
    AutoCloseable autoCloseable;
    @Mock
    ContentRepository contentRepository;
    PublishCommandHandler publishCommandHandler;
    DmsId id;

    @BeforeMethod
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        publishCommandHandler = new PublishCommandHandler(contentRepository);
        id = new DmsId("id");
    }

    @AfterMethod
    public void cleanUp() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void shouldDPublishContentWithDraftStatus() {
        //given
        Content content = contentWithStatus(PublishStatus.DRAFT);
        PublishCommand publishCommand = new PublishCommand(id);

        //when
        when(contentRepository.getById(id)).thenReturn(Optional.ofNullable(content));
        publishCommandHandler.publishContent(publishCommand);

        //then
        verify(contentRepository).save(content);
    }

    @Test
    public void shouldDPublishContentWithUnpublishedStatus() {
        //given
        Content content = contentWithStatus(PublishStatus.UNPUBLISHED);
        PublishCommand publishCommand = new PublishCommand(id);

        //when
        when(contentRepository.getById(id)).thenReturn(Optional.ofNullable(content));
        publishCommandHandler.publishContent(publishCommand);

        //then
        verify(contentRepository).save(content);
    }

    @Test
    public void shouldThrowsErrorWhileTryingPublishContentWithPublishedStatus() {
        //given
        Content content = contentWithStatus(PublishStatus.PUBLISHED);
        PublishCommand publishCommand = new PublishCommand(id);
        when(contentRepository.getById(id)).thenReturn(Optional.ofNullable(content));

        //then
        assertThrows(ContentAlreadyPublishedException.class, () -> {
            publishCommandHandler.publishContent(publishCommand);
        });

    }

    @Test
    public void shouldThrowErrorWhileTryingPublishNotExistingContent() {
        //given
        PublishCommand publishCommand = new PublishCommand(id);

        //then
        assertThrows(ContentNotExistException.class, () -> {
            publishCommandHandler.publishContent(publishCommand);
        });
    }

    private Content contentWithStatus(PublishStatus publishStatus) {
        return Content.builder()
                .dmsId(id)
                .title(new Title("title"))
                .pageDate(new PageDate(LocalDate.now()))
                .publishStatus(publishStatus)
                .build();
    }
}
