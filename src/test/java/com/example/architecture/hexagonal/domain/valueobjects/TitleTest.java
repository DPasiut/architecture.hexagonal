package com.example.architecture.hexagonal.domain.valueobjects;

import com.example.architecture.hexagonal.domain.exceptions.NullTitleException;
import com.example.architecture.hexagonal.domain.exceptions.TooLongTitleException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

@Test(testName = "Title length correctness")
public class TitleTest {
    @Test
    public void shouldThrowExceptionForNullValue() {
        assertThrows(NullTitleException.class, () -> {
            new Title(null);
        });
    }

    @Test
    public void shouldThrowExceptionForTooLongTitle() {
        assertThrows(TooLongTitleException.class, () -> {
            String title = "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789";
            new Title(title);
        });
    }

    @Test
    public void shouldCreateTitleWithCorrectValue() {
        Title title = new Title("Correct value");
        assertEquals(title.value(), "Correct value");
    }
}
