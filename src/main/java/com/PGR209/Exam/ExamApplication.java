package com.PGR209.Exam;

import com.PGR209.Exam.configuration.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamApplication {
	public static void main(String[] args) {
		System.out.println(new ApplicationConfiguration().getPageSize());
		SpringApplication.run(ExamApplication.class, args);
	}
}
