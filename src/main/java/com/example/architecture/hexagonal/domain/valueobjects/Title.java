package com.example.architecture.hexagonal.domain.valueobjects;

import com.example.architecture.hexagonal.domain.exceptions.NullTitleException;
import com.example.architecture.hexagonal.domain.exceptions.TooLongTitleException;

public record Title(String value) {
    private static final int MAX_LENGTH = 80;

    public Title {
        nullsValidation(value);
        lengthValidation(value);
    }

    private void nullsValidation(String value) {
        if (value == null) {
            throw new NullTitleException();
        }
    }

    private void lengthValidation(String value) {
        if (value.length() > MAX_LENGTH) {
            throw new TooLongTitleException();
        }
    }
}