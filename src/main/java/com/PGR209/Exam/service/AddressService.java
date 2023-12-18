package com.PGR209.Exam.service;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelNonNullableFieldException;
import com.PGR209.Exam.exception.ModelValuesNotAllowed;
import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.repository.AddressRepository;
import com.PGR209.Exam.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Address", id));
    }

    //REMOVE???
    public List<Address> getAddressAll() {
        return addressRepository.findAll();
    }

    public List<Address> getAddressPage(int page) {
        //Move to config file?
        int pageSize = 4;

        Pageable pageable = Pageable.ofSize(pageSize).withPage(page);
        return addressRepository.findAll(pageable).toList();
    }

    public Address newAddress(Address address) {
        List<Customer> addressCustomers = new ArrayList<>();

        if (address.getAddressName() == null || address.getAddressName().isEmpty()) {
            throw new ModelNonNullableFieldException("Address", "addressName");
        }

        for (Customer customer : address.getAddressCustomers()) {
            addressCustomers.add(customerRepository.findById(customer.getCustomerId())
                    .orElseThrow(() -> new ModelValuesNotAllowed("Customer", "Bob")));
        }

        Address newAddress = new Address(
                address.getAddressName(),
                addressCustomers
        );

        return addressRepository.save(newAddress);
    }

    public void deleteAddress(Long id) {
        if (addressRepository.findById(id).isEmpty()) {
            throw new ModelIdNotFoundException("Address", id);
        }

        addressRepository.deleteById(id);
    }

    public Address updateAddress(Address address, Long id) {
        Address returnAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Address", id));

        //SINGLE PROPERTY
        if (address.getAddressName() != null && !address.getAddressName().isEmpty()) {
            returnAddress.setAddressName(address.getAddressName());
        }

        //LIST
        for (Customer customer : address.getAddressCustomers()) {
            if (customer.getCustomerId() == null) {
                throw new ModelNonNullableFieldException("CustomerList", "customerId");
            } else if (customer.getCustomerId() < 1L) {
                throw new ModelValuesNotAllowed("CustomerList", "customerId");
            }

            returnAddress.getAddressCustomers().add(customerRepository.findById(customer.getCustomerId())
                    .orElseThrow(() -> new ModelIdNotFoundException("Customer", customer.getCustomerId())));
        }

        return addressRepository.save(returnAddress);
    }
}
