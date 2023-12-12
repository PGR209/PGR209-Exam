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

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getCustomerAll() {
        return customerRepository.findAll();
    }

    public Customer newCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Customer customer) {
        System.out.println("FIX ME");
        return null;
    }

    public Customer addAddress(Integer id, Address address) {
        System.out.println("FIX ME");
        return null;
    }
}
