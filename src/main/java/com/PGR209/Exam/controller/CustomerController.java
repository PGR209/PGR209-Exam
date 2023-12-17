package com.PGR209.Exam.controller;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelListEmptyException;
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
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Customer", id));
    }

    @GetMapping
    public List<Customer> getCustomerAll() {
        List<Customer> customers = customerService.getCustomerAll();

        if (!customers.isEmpty()) {
            return customers;
        } else {
            throw new ModelListEmptyException("Customer");
        }
    }

    @GetMapping("page/{pageNr}")
    public List<Customer> getCustomerPage(@PathVariable int pageNr) {
        List<Customer> customers = customerService.getCustomerPage(pageNr);

        if (!customers.isEmpty()) {
            return customers;
        } else {
            throw new ModelListEmptyException("Customer");
        }
    }

    @PostMapping
    public Customer newCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        return customerService.newCustomer(customer);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @PutMapping("{id}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        return customerService.updateCustomer(customer, id)
                .orElseThrow(() -> new ModelIdNotFoundException("Customer", id));
    }

    @PutMapping("{id}/address")
    public Customer addAddress(@PathVariable Integer id, Address address) {
        return customerService.addAddress(id, address);
    }
}
