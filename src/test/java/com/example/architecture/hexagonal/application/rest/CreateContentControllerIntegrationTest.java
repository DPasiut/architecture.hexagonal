package com.example.architecture.hexagonal.application.rest;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CreateContentControllerIntegrationTest extends AbstractTestNGSpringContextTests {

    @Autowired
    WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private AutoCloseable closeable;

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
    public void testCreateContent() throws Exception {
        String jsonContent = "{\"title\": \"This is title\",\"pageDate\": \"2022-07-12\",\"publishDate\": \"2022-07-14\"}";
        this.mockMvc.perform(put("/content")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }
}
