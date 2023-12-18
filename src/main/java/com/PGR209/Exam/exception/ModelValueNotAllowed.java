package com.PGR209.Exam.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class ModelValueNotAllowed extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String model;
    private final String field;

    public ModelValueNotAllowed(String model, String field) {
        this.model = model;
        this.field = field;
    }
}
