package com.example.architecture.hexagonal.domain.entity;


import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.PublicComment;
import com.example.architecture.hexagonal.domain.valueobjects.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

@Test(testName = "Slug generator")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestSlugGenerator {

    PageDate pageDate = new PageDate(LocalDate.of(2022, 6, 1));
    UpcomingDate upcomingDate = new UpcomingDate(LocalDate.of(2022, 6, 29));
    PublishDate publishDate = new PublishDate(LocalDate.of(2022, 10, 1));
    OpenForSubmissionDate openForSubmissionDate = new OpenForSubmissionDate(LocalDate.of(2022, 10, 2));
    CloseForSubmissionDate closeForSubmissionDate = new CloseForSubmissionDate(LocalDate.of(2022, 10, 30));

    @Test
    public void shouldGenerateSlugWithDefaultGenerator() {
        Content content = Content
                .builder()
                .pageDate(pageDate)
                .dmsId(new DmsId("dmsId"))
                .title(new Title("@#$%^&*   title!@- -- with lot- special   characters"))
                .build();
        content.generateSlug();
        assertEquals(content.getSlug().value(), "-title-with-lot-special-characters-2022-Jun-01");
    }

    @Test
    public void shouldGenerateSlugForPublicComment() {
        PublicComment publicComment = PublicComment
                .builder()
                .dmsId(new DmsId("id"))
                .title(new Title("@#$%^&*   title!@- -- with lot- special   characters"))
                .pageDate(pageDate)
                .upcomingDate(upcomingDate)
                .build();
        publicComment.generateSlug();
        assertEquals(publicComment.getSlug().value(), "-title-with-lot-special-characters-2022-Jun-01-2022-Jun-29");
    }

//    @Test
//    public void shouldGenerateSlugForPublicCommentWithJustPageDate() {
//        PublicComment publicComment = PublicComment
//                .builder()
//                .dmsId(new DmsId("id"))
//                .title(new Title("@#$%^&*   title!@- -- with lot- special   characters"))
//                .slug(null)
//                .pageDate(pageDate)
//                .publishStatus(PublishStatus.DRAFT)
//                .openForSubmissionDate(null)
//                .closeForSubmission(null)
//                .publishDate(null)
//                .upcomingDate(upcomingDate)
//                .build();
//        publicComment.generateSlug();
//        assertEquals(publicComment.getSlug().value(), "-title-with-lot-special-characters-2022-Jun-27-2022-Jun-29");
//    }
}
