package com.PGR209.Exam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @GetMapping("{id}")
    public void getCustomerById() {
        System.out.println("Customer by id");
    }

    @GetMapping
    public void getCustomerAll() {
        System.out.println("List of all customers");
    }
}
