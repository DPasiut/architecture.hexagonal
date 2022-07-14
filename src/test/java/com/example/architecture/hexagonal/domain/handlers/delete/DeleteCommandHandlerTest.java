package com.example.architecture.hexagonal.domain.handlers.delete;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.exceptions.ContentNotExistException;
import com.example.architecture.hexagonal.domain.exceptions.NotAllowedDeletePublishedContent;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import com.example.architecture.hexagonal.domain.service.DmsIdService;
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
import static org.testng.Assert.assertThrows;

@Test(testName = "Deleting Content")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteCommandHandlerTest {
    AutoCloseable autoCloseable;
    @Mock
    ContentRepository contentRepository;
    DeleteCommandHandler deleteCommandHandler;
    DmsId id;

    @BeforeMethod
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        deleteCommandHandler = new DeleteCommandHandler(contentRepository);
        id = new DmsId("id");
    }

    @AfterMethod
    public void cleanUp() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void shouldDeleteContentWithStatusOtherThanPublished() {
        //given
        Content content = Content.builder()
                .dmsId(id)
                .title(new Title("title"))
                .pageDate(new PageDate(LocalDate.now()))
                .publishStatus(PublishStatus.DRAFT)
                .build();
        DeleteCommand deleteCommand = new DeleteCommand(id);

        //when
        when(contentRepository.getById(id)).thenReturn(Optional.ofNullable(content));
        deleteCommandHandler.deleteContent(deleteCommand);

        //then
        verify(contentRepository).delete(deleteCommand.id());
    }

    @Test
    public void shouldNotDeleteContentWithStatusEqualsPublished() {
        //given
        Content content = Content.builder()
                .dmsId(id)
                .title(new Title("title"))
                .pageDate(new PageDate(LocalDate.now()))
                .publishStatus(PublishStatus.PUBLISHED)
                .build();
        DeleteCommand deleteCommand = new DeleteCommand(id);

        //when
        when(contentRepository.getById(id)).thenReturn(Optional.ofNullable(content));

        //then
        assertThrows(NotAllowedDeletePublishedContent.class, () -> {
            deleteCommandHandler.deleteContent(deleteCommand);
        });
    }

    @Test
    public void shouldThrowErrorWhileTryingDeleteNotExistingContent() {
        //given
        DeleteCommand deleteCommand = new DeleteCommand(id);

        //then
        assertThrows(ContentNotExistException.class, () -> {
            deleteCommandHandler.deleteContent(deleteCommand);
        });
    }
}
