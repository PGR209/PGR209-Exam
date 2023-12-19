package com.PGR209.Exam.configuration;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ApplicationConfiguration {
    private final int pageSize = 4;
}
