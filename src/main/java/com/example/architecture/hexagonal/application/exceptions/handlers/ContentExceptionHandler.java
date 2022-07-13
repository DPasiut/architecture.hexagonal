package com.example.architecture.hexagonal.application.exceptions.handlers;

import com.example.architecture.hexagonal.domain.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ContentExceptionHandler {
    @ExceptionHandler({ContentNotFoundException.class, ContentNotExistException.class})
    public ResponseEntity<Object> handleContentNotExist(Exception exception) {
        return new ResponseEntity<>(
                exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ContentAlreadyPublishedException.class, ContentNotPublishedException.class})
    public ResponseEntity<Object> handleContentPublishedException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAllowedDeletePublishedContent.class)
    public ResponseEntity<Object> handleNotAllowDeletePublishedContent(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
