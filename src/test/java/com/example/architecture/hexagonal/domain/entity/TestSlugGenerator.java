package com.example.architecture.hexagonal.domain.entity;


import com.example.architecture.hexagonal.domain.valueobjects.*;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

@Test(testName = "Slug generator")
public class TestSlugGenerator {

    @Test
    public void testContentSlugGenerator(){
        PageDate date = new PageDate(LocalDate.of(2022, 6,27));
        Content content = new Content(new DmsId("dmsId"), new Title("@#$%^&*   title!@- -- with lot- special   characters"), date);

        content.generateSlug();

        assertEquals(content.slug.value(),"-title-with-lot-special-characters-2022-Jun-27");
    }

    @Test
    public void testPublicCommentSlugGenerator(){
        PageDate pageDate = new PageDate(LocalDate.of(2022, 6,27));
        UpcomingDate upcomingDate = new UpcomingDate(pageDate.value().plusDays(2));

        PublicComment publicComment = new PublicComment(new DmsId("dmsId"), new Title("@#$%^&*   title!@- -- with lot- special   characters"), pageDate, upcomingDate);

        publicComment.generateSlug();

        assertEquals(publicComment.slug.value(), "-title-with-lot-special-characters-2022-Jun-27-2022-Jun-29");
    }
}
