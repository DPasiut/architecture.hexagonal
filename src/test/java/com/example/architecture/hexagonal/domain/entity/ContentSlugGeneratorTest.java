package com.example.architecture.hexagonal.domain.entity;


import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

@Test(testName = "Content slug generator")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentSlugGeneratorTest {

    final PageDate pageDate = new PageDate(LocalDate.of(2022, 6, 1));
    final PublishDate publishDate = new PublishDate(LocalDate.of(2022, 10, 1));

    @Test
    public void shouldGenerateSlugBasedOnTitlePlusDateWithoutSpecialCharactersAndReplaceEmptySpacesWithSingleDashCharacter() {
        Content content = Content
                .builder()
                .pageDate(pageDate)
                .dmsId(new DmsId("dmsId"))
                .title(new Title("@#$%^&*   title!@- -- with lot- special   characters"))
                .build();
        content.generateSlug();
        assertEquals(content.getSlug().value(), "title-with-lot-special-characters-2022-Jun-01");
    }

    @Test
    public void shouldNotGenerateNewSlugWhileSlugExist() {
        Content content = Content
                .builder()
                .dmsId(new DmsId("id"))
                .title(new Title("@#$%^&*   title!@- -- with lot- special   characters"))
                .slug(new Slug("slug-2022-Jun-29"))
                .pageDate(pageDate)
                .publishStatus(PublishStatus.DRAFT)
                .publishDate(publishDate)
                .build();
        content.generateSlug();
        assertEquals(content.getSlug().value(), "slug-2022-Jun-29");
    }
}
