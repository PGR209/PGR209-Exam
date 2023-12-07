package com.PGR209.Exam.controller;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @GetMapping("{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        System.out.println("Customer by id");
        return null;
    }

    @GetMapping
    public List<Customer> getCustomerAll() {
        System.out.println("List of all customers");
        return new ArrayList<>();
    }

    @PostMapping
    public Customer newCustomer(@RequestBody Customer customer) {
        System.out.println("Add new customer");
        return customer;
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        System.out.println("Delete customer");
    }

    @PutMapping
    public Customer updateCustomer(Customer customer) {
        System.out.println("Update customer");
        return customer;
    }


    @PutMapping("{id}")
    public Customer addAddress(@PathVariable Integer id, Address address) {
        System.out.println("Add address to customer");
        return null;
    }
}
