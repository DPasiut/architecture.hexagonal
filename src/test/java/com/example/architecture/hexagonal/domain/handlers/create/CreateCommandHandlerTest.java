package com.example.architecture.hexagonal.domain.handlers.create;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import com.example.architecture.hexagonal.domain.service.DmsIdService;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

@Test(testName = "Creating Content")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCommandHandlerTest {

    AutoCloseable autoCloseable;
    CreateCommandHandler createCommandHandler;
    @Mock
    ContentRepository contentRepository;
    @Mock
    DmsIdService dmsIdService;
    Content content;
    ContentDto contentDto;

    @BeforeMethod
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        createCommandHandler = new CreateCommandHandler(contentRepository, dmsIdService);
        LocalDate date = LocalDate.now();
        DmsId id = new DmsId("randomUUID");

        contentDto = ContentDto.builder()
                .title(new Title("title"))
                .pageDate(new PageDate(date))
                .publishDate(new PublishDate(date))
                .build();

        content = Content.builder()
                .dmsId(id)
                .title(contentDto.getTitle())
                .pageDate(contentDto.getPageDate())
                .publishDate(contentDto.getPublishDate())
                .publishStatus(PublishStatus.DRAFT)
                .build();

        when(dmsIdService.generateId()).thenReturn(id);
    }

    @AfterMethod
    public void cleanUp() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void shouldCreateContent() {
        //given
        CreateCommand createCommand = new CreateCommand(contentDto);

        //when
        createCommandHandler.createContent(createCommand);

        //then
        verify(contentRepository).save(content);
    }
}
