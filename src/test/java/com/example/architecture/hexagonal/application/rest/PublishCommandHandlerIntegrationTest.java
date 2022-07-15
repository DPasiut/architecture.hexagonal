package com.example.architecture.hexagonal.application.rest;

import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.DmsId;
import com.example.architecture.hexagonal.domain.valueobjects.PageDate;
import com.example.architecture.hexagonal.domain.valueobjects.PublishDate;
import com.example.architecture.hexagonal.domain.valueobjects.Title;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PublishCommandHandlerIntegrationTest extends AbstractTestNGSpringContextTests {
    private final static String BASE_URL = "/content/";
    private final static String CONTENT_ID = "id";
    private MockMvc mockMvc;
    private AutoCloseable closeable;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ContentRepository contentRepository;

    @BeforeMethod
    public void initController() {
        closeable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @AfterMethod
    public void cleanup() throws Exception {
        closeable.close();
    }

    @Test
    public void testShouldReturn200ForPublishUnpublishedContent() throws Exception {
        addContentWithStatus(PublishStatus.DRAFT);

        mockMvc.perform(post(BASE_URL + CONTENT_ID + "/publish"))
                .andExpect(status().isOk());
    }

    @Test
    public void testShouldReturn400ForPublishAlreadyPublishedContent() throws Exception {
        addContentWithStatus(PublishStatus.PUBLISHED);

        mockMvc.perform(post(BASE_URL + CONTENT_ID + "/publish"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testShouldReturn404ForPublishNotExistingContent() throws Exception {
        mockMvc.perform(post(BASE_URL + CONTENT_ID + "/publish"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testShouldReturn200ForUnpublishPublishedContent() throws Exception {
        addContentWithStatus(PublishStatus.PUBLISHED);

        mockMvc.perform(post(BASE_URL + CONTENT_ID + "/unpublish"))
                .andExpect(status().isOk());
    }

    @Test
    public void testShouldReturn400ForUnpublishDraftContent() throws Exception {
        addContentWithStatus(PublishStatus.DRAFT);

        mockMvc.perform(post(BASE_URL + CONTENT_ID + "/unpublish"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testShouldReturn404ForUnpublishNotExistingContent() throws Exception {
        mockMvc.perform(post(BASE_URL + CONTENT_ID + "/unpublish"))
                .andExpect(status().isNotFound());
    }

    private void addContentWithStatus(PublishStatus status) {
        Content content = Content.builder()
                .dmsId(new DmsId(CONTENT_ID))
                .title(new Title("title"))
                .pageDate(new PageDate(LocalDate.now()))
                .publishDate(new PublishDate(LocalDate.now()))
                .publishStatus(status)
                .build();
        contentRepository.save(content);
    }
}
