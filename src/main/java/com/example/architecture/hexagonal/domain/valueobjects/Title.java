package com.example.architecture.hexagonal.domain.valueobjects;

import com.example.architecture.hexagonal.domain.errors.TooLongTitleException;

public record Title(String value) {
    private static final int MAX_LENGTH = 80;

    public Title {
        if (value == null) {
            throw new NullPointerException("Title can't be null");
        }
        if (value.length() > MAX_LENGTH) {
            throw new TooLongTitleException("The title is too long. Max 80 characters");
        }
    }
}