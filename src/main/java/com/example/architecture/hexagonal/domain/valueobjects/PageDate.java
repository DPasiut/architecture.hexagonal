package com.example.architecture.hexagonal.domain.valueobjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record PageDate(LocalDate value) {
    private static final String PATTERN = "yyyy-MMM-dd";
    public PageDate(LocalDate value){
        String formattedDate = value.format(DateTimeFormatter.ofPattern(PATTERN));
        this.value = LocalDate.parse(formattedDate);
    }
}