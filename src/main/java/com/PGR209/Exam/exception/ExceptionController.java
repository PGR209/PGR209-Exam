package com.PGR209.Exam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class ExceptionController {
    @ExceptionHandler(ModelIdNotFoundException.class)
    public ResponseEntity<Object> exception(ModelIdNotFoundException exception) {
        System.out.println(this);
        return new ResponseEntity<>(String.format("%s with id %s not found.", exception.getModel(), exception.getId()), HttpStatus.NOT_FOUND);
    }
}
