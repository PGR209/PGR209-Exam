package com.PGR209.Exam.service;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getCustomerAll() {
        return customerRepository.findAll();
    }

    public List<Customer> getCustomerAll(int pageSize, int page) {

        Pageable pageable = Pageable.ofSize(pageSize).withPage(page);
        return customerRepository.findAll(pageable).toList();
    }

    public Customer newCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Optional<Customer> updateCustomer(Customer customer, Long id) {
        Optional<Customer> returnCustomer = customerRepository.findById(id);

        if (returnCustomer.isPresent()) {
            customer.setId(id);

            returnCustomer = Optional.of(customerRepository.save(customer));
        }

        return returnCustomer;
    }

    public Customer addAddress(Integer id, Address address) {
        System.out.println("FIX ME");
        return null;
    }
}
