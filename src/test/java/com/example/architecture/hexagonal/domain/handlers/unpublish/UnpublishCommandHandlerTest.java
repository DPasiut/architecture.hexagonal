package com.example.architecture.hexagonal.domain.handlers.unpublish;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.exceptions.ContentAlreadyPublishedException;
import com.example.architecture.hexagonal.domain.exceptions.ContentNotExistException;
import com.example.architecture.hexagonal.domain.exceptions.ContentNotPublishedException;
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
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

@Test(testName = "Unpublish Content")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UnpublishCommandHandlerTest {
    AutoCloseable autoCloseable;
    @Mock
    ContentRepository contentRepository;
    UnpublishCommandHandler unpublishCommandHandler;
    DmsId id;

    @BeforeMethod
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        unpublishCommandHandler = new UnpublishCommandHandler(contentRepository);
        id = new DmsId("id");
    }

    @AfterMethod
    public void cleanUp() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void shouldUnpublishContentWithPublishedStatus() {
        //given
        Content content = contentWithStatus(PublishStatus.PUBLISHED);
        UnpublishCommand unpublishCommand = new UnpublishCommand(id);

        //when
        when(contentRepository.getById(id)).thenReturn(Optional.ofNullable(content));
        unpublishCommandHandler.unpublishContent(unpublishCommand);

        //then
        verify(contentRepository).save(content);

        //and
        assertEquals(content.getPublishStatus(), PublishStatus.UNPUBLISHED);
    }

    @Test
    public void shouldNotUnpublishContentWithDraftStatus() {
        //given
        Content content = contentWithStatus(PublishStatus.DRAFT);
        UnpublishCommand unpublishCommand = new UnpublishCommand(id);
        when(contentRepository.getById(id)).thenReturn(Optional.ofNullable(content));

        //then
        assertThrows(ContentNotPublishedException.class, () -> {
            unpublishCommandHandler.unpublishContent(unpublishCommand);
        });
    }

    @Test
    public void shouldNotUnpublishContentWithUnpublishedStatus() {
        //given
        Content content = contentWithStatus(PublishStatus.UNPUBLISHED);
        UnpublishCommand unpublishCommand = new UnpublishCommand(id);
        when(contentRepository.getById(id)).thenReturn(Optional.ofNullable(content));

        //then
        assertThrows(ContentNotPublishedException.class, () -> {
            unpublishCommandHandler.unpublishContent(unpublishCommand);
        });
    }

    @Test
    public void shouldThrowsErrorWhileTryingPublishContentWithPublishedStatus() {
        //given
        Content content = contentWithStatus(PublishStatus.DRAFT);
        UnpublishCommand unpublishCommand = new UnpublishCommand(id);
        when(contentRepository.getById(id)).thenReturn(Optional.ofNullable(content));

        //then
        assertThrows(ContentAlreadyPublishedException.class, () -> {
            unpublishCommandHandler.unpublishContent(unpublishCommand);
        });

    }

    @Test
    public void shouldThrowErrorWhileTryingPublishNotExistingContent() {
        //given
        UnpublishCommand unpublishCommand = new UnpublishCommand(id);

        //then
        assertThrows(ContentNotExistException.class, () -> {
            unpublishCommandHandler.unpublishContent(unpublishCommand);
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
