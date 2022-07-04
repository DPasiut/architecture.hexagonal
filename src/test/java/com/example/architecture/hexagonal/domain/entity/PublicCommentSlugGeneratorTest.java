package com.example.architecture.hexagonal.domain.entity;


import com.example.architecture.hexagonal.domain.PublicComment;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

@Test(testName = "Public comment slug generator")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublicCommentSlugGeneratorTest {

    final PageDate pageDate = new PageDate(LocalDate.of(2022, 6, 1));
    final UpcomingDate upcomingDate = new UpcomingDate(LocalDate.of(2022, 6, 29));
    final PublishDate publishDate = new PublishDate(LocalDate.of(2022, 10, 1));
    final OpenForSubmissionDate openForSubmissionDate = new OpenForSubmissionDate(LocalDate.of(2022, 10, 2));
    final CloseForSubmissionDate closeForSubmissionDate = new CloseForSubmissionDate(LocalDate.of(2022, 10, 30));
    final ReportDue reportDue = new ReportDue(LocalDate.of(2022, 10, 30));

    @Test
    public void shouldGenerateSlugBasedOnTitleAndUpcomingDate() {
        PublicComment publicComment = PublicComment
                .builder()
                .dmsId(new DmsId("id"))
                .title(new Title("@#$%^&*   title!@- -- with lot- special   characters"))
                .pageDate(pageDate)
                .upcomingDate(upcomingDate)
                .build();
        publicComment.generateSlug();
        assertEquals(publicComment.getSlug().value(), "title-with-lot-special-characters-2022-Jun-29");
    }

    @Test
    public void shouldGenerateSlugBasedOnTitlePlusJustUpcomingDateWhileTheRestDatesAreEqualsToUpcomingDate() {
        PublicComment publicComment = PublicComment
                .builder()
                .dmsId(new DmsId("id"))
                .title(new Title("@#$%^&*   title!@- -- with lot- special   characters"))
                .pageDate(pageDate)
                .publishStatus(PublishStatus.DRAFT)
                .openForSubmissionDate(new OpenForSubmissionDate(upcomingDate.value()))
                .closeForSubmission(new CloseForSubmissionDate(upcomingDate.value()))
                .publishDate(new PublishDate(upcomingDate.value()))
                .reportDue(new ReportDue(upcomingDate.value()))
                .upcomingDate(upcomingDate)
                .build();
        publicComment.generateSlug();
        assertEquals(publicComment.getSlug().value(), "title-with-lot-special-characters-2022-Jun-29");
    }

    @Test
    public void shouldGenerateSlugBasedOnTitlePlusUpcomingDatePlusOpenForSubmissionDate() {
        PublicComment publicComment = PublicComment
                .builder()
                .dmsId(new DmsId("id"))
                .title(new Title("@#$%^&*   title!@- -- with lot- special   characters"))
                .pageDate(pageDate)
                .publishStatus(PublishStatus.DRAFT)
                .openForSubmissionDate(openForSubmissionDate)
                .upcomingDate(upcomingDate)
                .publishDate(publishDate)
                .build();
        publicComment.generateSlug();
        assertEquals(publicComment.getSlug().value(), "title-with-lot-special-characters-2022-Jun-29-2022-Oct-02");
    }

    @Test
    public void shouldGenerateSlugBasedOnTitlePlusUpcomingDatePlusCloseForSubmissionDate() {
        PublicComment publicComment = PublicComment
                .builder()
                .dmsId(new DmsId("id"))
                .title(new Title("@#$%^&*   title!@- -- with lot- special   characters"))
                .pageDate(pageDate)
                .publishStatus(PublishStatus.DRAFT)
                .openForSubmissionDate(openForSubmissionDate)
                .closeForSubmission(closeForSubmissionDate)
                .publishDate(publishDate)
                .upcomingDate(upcomingDate)
                .build();
        publicComment.generateSlug();
        assertEquals(publicComment.getSlug().value(), "title-with-lot-special-characters-2022-Jun-29-2022-Oct-30");
    }

    @Test
    public void shouldGenerateSlugBasedOnTitlePlusUpcomingDatePlusReportDue() {
        PublicComment publicComment = PublicComment
                .builder()
                .dmsId(new DmsId("id"))
                .title(new Title("@#$%^&*   title!@- -- with lot- special   characters"))
                .pageDate(pageDate)
                .publishStatus(PublishStatus.DRAFT)
                .reportDue(reportDue)
                .upcomingDate(upcomingDate)
                .build();
        publicComment.generateSlug();
        assertEquals(publicComment.getSlug().value(), "title-with-lot-special-characters-2022-Jun-29-2022-Oct-30");
    }
}
