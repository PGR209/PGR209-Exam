package com.PGR209.Exam.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class ModelIdNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String model;
    private final Long id;

    public ModelIdNotFoundException(String model, Long id) {
        this.model = model;
        this.id = id;
    }
}