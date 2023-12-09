package com.PGR209.Exam.controller;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<Customer> getCustomerAll() {
        return customerService.getCustomerAll();
    }

    @PostMapping
    public Customer newCustomer(@RequestBody Customer customer) {
        return customerService.newCustomer(customer);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
    }

    @PutMapping
    public Customer updateCustomer(Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @PutMapping("{id}")
    public Customer addAddress(@PathVariable Integer id, Address address) {
        return customerService.addAddress(id, address);
    }
}
