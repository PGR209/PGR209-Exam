package com.PGR209.Exam.service;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelValuesNotAllowed;
import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.repository.AddressRepository;
import com.PGR209.Exam.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Customer", id));
    }

    public List<Customer> getCustomerPage(int page) {
        //Move to config file?
        int pageSize = 4;

        Pageable pageable = Pageable.ofSize(pageSize).withPage(page);
        return customerRepository.findAll(pageable).toList();
    }

    public Optional<Customer> newCustomer(Customer customer) {
        try {
            return Optional.of(customerRepository.save(customer));
        } catch (DataIntegrityViolationException error) {
            return Optional.empty();
        }
    }

    public void deleteCustomer(Long id) {
        if (customerRepository.findById(id).isEmpty()) {
            throw new ModelIdNotFoundException("Customer", id);
        }

        customerRepository.deleteById(id);
    }

    public Optional<Customer> updateCustomer(Customer customer, Long id) {
        Optional<Customer> returnCustomer = customerRepository.findById(id);

        if (returnCustomer.isPresent()) {
            customer.setCustomerId(id);

            returnCustomer = Optional.of(customerRepository.save(customer));
        }

        return returnCustomer;
    }

    public Customer addAddress(Long id, Address address) {
        List<Address> addresses;
        Customer updatedCustomer;

        updatedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Customer", id));

        addresses = updatedCustomer.getCustomerAddresses();
        addresses.add(addressRepository.findById(address.getAddressId())
                .orElseThrow(() -> new ModelIdNotFoundException("Address", id)));

        return customerRepository.save(updatedCustomer);
    }

    /*
    public Address addCustomer(Long id, Customer customer) {
        List<Customer> customers;
        Address updatedAddress;

        updatedAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Address", id));

        customers = updatedAddress.getCustomers();
        customers.add(customerRepository.findById(customer.getId())
                .orElseThrow(() -> new ModelIdNotFoundException("Customer", customer.getId())));

        return addressRepository.save(updatedAddress);
    }

     */
}
