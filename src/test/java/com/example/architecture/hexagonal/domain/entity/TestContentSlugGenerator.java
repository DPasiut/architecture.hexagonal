package com.example.architecture.hexagonal.domain.entity;


import com.example.architecture.hexagonal.domain.valueobjects.DmsId;
import com.example.architecture.hexagonal.domain.valueobjects.PageDate;
import com.example.architecture.hexagonal.domain.valueobjects.Title;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

@Test(testName = "Slug generator")
public class TestContentSlugGenerator {

    @Test
    public void testSlugGenerator(){
        PageDate date = new PageDate(LocalDate.now());
        Content content = new Content(new DmsId("dmsId"), new Title("@#$%^&*   title!@- -- with lot- special   characters"), date);

        content.generateSlug();

        assertEquals(content.slug.value(),"-title-with-lot-special-characters-2022-06-27");
    }

}
