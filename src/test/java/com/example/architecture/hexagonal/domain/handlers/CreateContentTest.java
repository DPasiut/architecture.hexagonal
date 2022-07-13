package com.example.architecture.hexagonal.domain.handlers;

import com.example.architecture.hexagonal.domain.handlers.create.CreateCommand;
import com.example.architecture.hexagonal.domain.handlers.create.CreateCommandHandler;
import com.example.architecture.hexagonal.domain.valueobjects.*;
import com.example.architecture.hexagonal.infrastructure.InMemoryContentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

@Test(testName = "Creating Content")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateContentTest {
    final InMemoryContentRepository inMemoryContentRepository = new InMemoryContentRepository();
    final CreateCommandHandler createCommandHandler = new CreateCommandHandler(inMemoryContentRepository);
    final LocalDate DATE = LocalDate.now();
    @Test
    public void shouldCreateContent() {
        ContentDto contentDto = ContentDto.builder()
                .title(new Title("title"))
                .pageDate(new PageDate(DATE))
                .publishDate(new PublishDate(DATE))
                .build();
        inMemoryContentRepository.clearRepository();
        assertEquals(inMemoryContentRepository.size(), 0);
        createCommandHandler.createContent(new CreateCommand(contentDto));
        assertEquals(inMemoryContentRepository.size(), 1);
    }
}
