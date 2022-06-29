package com.example.architecture.hexagonal.domain.exceptions;

public class NullTitleException extends RuntimeException {
    public NullTitleException() {
        super("The title can't be null");
    }
}
