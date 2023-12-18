package com.PGR209.Exam.exception;

import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serial;

@Getter
public class ModelValuesNotAllowed extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String model;
    private final String field;

    public ModelValuesNotAllowed(String model, String field) {
        this.model = model;
        this.field = field;
    }
}