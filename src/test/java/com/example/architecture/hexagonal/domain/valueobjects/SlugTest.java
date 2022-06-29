package com.example.architecture.hexagonal.domain.valueobjects;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(testName = "Slug replace characters according to rules")
public class SlugTest {
    @Test
    public void shouldRemoveAllSpecialCharactersExceptDashAndReplaceWhiteSpacesAndRepeatedDashesWithSingleDash() {
        Slug slug = Slug.fromRawString("@#$%^&*   title!@- -- with lot----- special   -!@? ££ § § @ @ @     chara!cter##s");
        assertEquals(slug.value(), "title-with-lot-special-characters");
    }
}
