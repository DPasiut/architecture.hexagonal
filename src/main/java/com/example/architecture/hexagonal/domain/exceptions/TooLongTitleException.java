package com.example.architecture.hexagonal.domain.exceptions;

public class TooLongTitleException extends RuntimeException {
    public TooLongTitleException() {
        super("The title is too long. Max 80 characters");
    }
}
