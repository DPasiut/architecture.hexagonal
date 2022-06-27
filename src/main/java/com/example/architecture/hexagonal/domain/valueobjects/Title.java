package com.example.architecture.hexagonal.domain.valueobjects;

import com.example.architecture.hexagonal.domain.errors.TooLongTitleException;

public record Title(String value) {
    public Title{
        if(value.length() > 80){
            throw new TooLongTitleException("The title is too long. Max 80 characters");
        }
    }
}