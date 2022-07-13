package com.example.architecture.hexagonal.domain.handlers;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.handlers.create.CreateCommandHandler;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.DmsId;
import com.example.architecture.hexagonal.domain.valueobjects.PageDate;
import com.example.architecture.hexagonal.domain.valueobjects.Title;
import com.example.architecture.hexagonal.infrastructure.InMemoryContentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

@Test(testName = "Creating Content")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateContentTest {
    InMemoryContentRepository inMemoryContentRepository = new InMemoryContentRepository();
    final CreateCommandHandler createCommandHandler = new CreateCommandHandler(inMemoryContentRepository);
    final DmsId ID = new DmsId("newId");
    @Test
    public void shouldCreateContent(){
        Content content = Content.builder()
                .dmsId(ID)
                .title(new Title("title"))
                .pageDate(new PageDate(LocalDate.now()))
                .publishStatus(PublishStatus.PUBLISHED)
                .build();
        inMemoryContentRepository.save(content);
        assertEquals(inMemoryContentRepository.getById(ID).get(), content);
    }
}
