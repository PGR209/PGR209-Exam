package com.PGR209.Exam.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AddressExceptionController {
    @ExceptionHandler(value = ModelNotFoundException.class)
    public ResponseEntity<Object> exception(ModelNotFoundException exception) {
        return new ResponseEntity<>("Address not found.", HttpStatus.NOT_FOUND);
    }
}
