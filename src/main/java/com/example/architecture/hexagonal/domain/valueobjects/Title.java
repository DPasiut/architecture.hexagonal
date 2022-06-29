package com.example.architecture.hexagonal.domain.valueobjects;

import com.example.architecture.hexagonal.domain.exceptions.NullTitleException;
import com.example.architecture.hexagonal.domain.exceptions.TooLongTitleException;

public record Title(String value) {
    private static final int MAX_LENGTH = 80;

    public Title {
        nullsValidation();
        lengthValidation();
    }

    private void lengthValidation() {
        if (value.length() > MAX_LENGTH) {
            throw new TooLongTitleException();
        }
    }

    private void nullsValidation() {
        if (value == null) {
            throw new NullTitleException();
        }
    }
}