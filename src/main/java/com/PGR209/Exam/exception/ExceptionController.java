package com.PGR209.Exam.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class ExceptionController {
    Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(ModelIdNotFoundException.class)
    public ResponseEntity<Object> exception(ModelIdNotFoundException exception) {
        String returnBody = String.format("%s with id %s not found.", exception.getModel(), exception.getId());

        logger.warn(returnBody);

        return new ResponseEntity<>(returnBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ModelListEmptyException.class)
    public ResponseEntity<Object> exception(ModelListEmptyException exception) {
        String returnBody = String.format("%s list empty.", exception.getModel());

        logger.warn(returnBody);

        return new ResponseEntity<>(returnBody, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ModelValueNotAllowed.class)
    public ResponseEntity<Object> exception(ModelValueNotAllowed exception) {
        String returnBody = String.format("Unsupported values for %s field in %s.", exception.getField(), exception.getModel());

        logger.warn(returnBody);

        return new ResponseEntity<>(returnBody, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(ModelNonNullableFieldException.class)
    public ResponseEntity<Object> exception(ModelNonNullableFieldException exception) {
        String returnBody = String.format("Non-nullable %s field cannot be empty in %s.", exception.getField(), exception.getModel());

        logger.warn(returnBody);

        return new ResponseEntity<>(returnBody, HttpStatus.NOT_ACCEPTABLE);
    }
}