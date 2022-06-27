package com.example.architecture.hexagonal.domain.errors;

public class TooLongTitleException extends RuntimeException {
    public TooLongTitleException(String s) {
        System.out.println(s);
    }
}
