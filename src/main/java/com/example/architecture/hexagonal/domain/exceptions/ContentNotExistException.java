package com.example.architecture.hexagonal.domain.exceptions;

public class ContentNotExistException extends RuntimeException {
    public ContentNotExistException() {
        super("Content not exist");
    }
}
