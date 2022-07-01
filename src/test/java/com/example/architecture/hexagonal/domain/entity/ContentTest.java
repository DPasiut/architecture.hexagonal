package com.example.architecture.hexagonal.domain.entity;


import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.exceptions.ContentNotPublishedException;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

@Test(testName = "Content publication")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentTest {
    final PageDate pageDate = new PageDate(LocalDate.of(2022, 6, 1));
    @Test
    public void shouldPublishContentAndGenerateSlugWhenPublishStatusEqualsDraft() {
        Content content = Content
                .builder()
                .dmsId(new DmsId("id"))
                .title(new Title("publish content"))
                .pageDate(pageDate)
                .publishStatus(PublishStatus.DRAFT)
                .build();

        assertNull(content.getSlug());
        assertEquals(PublishStatus.DRAFT, content.getPublishStatus());

        content.publish();

        assertEquals(PublishStatus.PUBLISHED, content.getPublishStatus());
        assertEquals("publish-content-2022-Jun-01", content.getSlug().value());
    }

    @Test
    public void shouldPublishContentAndNotGenerateNewSlugWhenPublishStatusEqualsUnpublished() {
        Content content = Content
                .builder()
                .dmsId(new DmsId("id"))
                .title(new Title("publish content"))
                .pageDate(pageDate)
                .slug(new Slug("publish-content-2022-Jun-29"))
                .publishStatus(PublishStatus.UNPUBLISHED)
                .build();

        assertEquals(PublishStatus.UNPUBLISHED, content.getPublishStatus());
        assertEquals("publish-content-2022-Jun-29", content.getSlug().value());

        content.publish();

        assertEquals(PublishStatus.PUBLISHED, content.getPublishStatus());
        assertEquals("publish-content-2022-Jun-29", content.getSlug().value());
    }

    @Test
    public void shouldUnpublishContentWhenPublishStatusEqualsPublished() {
        Content content = Content
                .builder()
                .dmsId(new DmsId("id"))
                .title(new Title("publish content"))
                .pageDate(pageDate)
                .slug(new Slug("publish-content-2022-Jun-29"))
                .publishStatus(PublishStatus.PUBLISHED)
                .build();

        assertEquals(PublishStatus.PUBLISHED, content.getPublishStatus());
        content.unpublish();
        assertEquals(PublishStatus.UNPUBLISHED, content.getPublishStatus());
    }

    @Test
    public void shouldNotUnpublishContentWhenPublishStatusEqualsDraft() {
        Content content = Content
                .builder()
                .dmsId(new DmsId("id"))
                .title(new Title("publish content"))
                .pageDate(pageDate)
                .slug(new Slug("publish-content-2022-Jun-29"))
                .publishStatus(PublishStatus.DRAFT)
                .build();

        assertEquals(PublishStatus.DRAFT, content.getPublishStatus());
        assertThrows(ContentNotPublishedException.class, content::unpublish);
        assertEquals(PublishStatus.DRAFT, content.getPublishStatus());
    }
}
