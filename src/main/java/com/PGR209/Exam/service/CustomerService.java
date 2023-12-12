package com.PGR209.Exam.service;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(Integer id) {
        System.out.println("Get customer by id " + id);
        return null;
    }

    public List<Customer> getCustomerAll() {
        System.out.println("Get all customers");
        return new ArrayList<>();
    }

    public Customer newCustomer(Customer customer) {
        System.out.println("Create new customer");
        return customer;
    }

    public void deleteCustomer(Integer id) {
        System.out.println("Delete customer with id " + id);
    }

    public Customer updateCustomer(Customer customer) {
        System.out.println("Update customer");
        return null;
    }

    public Customer addAddress(Integer id, Address address) {
        System.out.println("Add address to customer with id " + id);
        return null;
    }
}
