package com.PGR209.Exam.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class ModelListEmptyException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String model;

    public ModelListEmptyException(String model) {
        this.model = model;
    }
}
