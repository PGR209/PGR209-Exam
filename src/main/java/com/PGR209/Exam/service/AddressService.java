package com.PGR209.Exam.service;

import com.PGR209.Exam.configuration.ApplicationConfiguration;
import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelNonNullableFieldException;
import com.PGR209.Exam.exception.ModelValueNotAllowed;
import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.repository.AddressRepository;
import com.PGR209.Exam.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final ApplicationConfiguration applicationConfiguration;

    @Autowired
    public AddressService(AddressRepository addressRepository, CustomerRepository customerRepository, ApplicationConfiguration applicationConfiguration) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
        this.applicationConfiguration = applicationConfiguration;
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Address", id));
    }

    public List<Address> getAddressPage(int page) {
        Pageable pageable = Pageable.ofSize(applicationConfiguration.getPageSize()).withPage(page);
        System.out.println(applicationConfiguration.getPageSize());
        return addressRepository.findAll(pageable).toList();
    }

    public Address newAddress(Address address) {
        Address createdAddress;
        List<Customer> addressCustomers = new ArrayList<>();

        if (address.getAddressName() == null || address.getAddressName().isEmpty()) {
            throw new ModelNonNullableFieldException("Address", "addressName");
        }

        for (Customer customer : address.getAddressCustomers()) {
            addressCustomers.add(customerRepository.findById(customer.getCustomerId())
                    .orElseThrow(() -> new ModelValueNotAllowed("Customer", "customerId")));
        }

        createdAddress = new Address(
                address.getAddressName(),
                addressCustomers
        );

        for (Customer customer : createdAddress.getAddressCustomers()) {
            customer.getCustomerAddresses().add(createdAddress);
        }

        return addressRepository.save(createdAddress);
    }

    public void deleteAddress(Long id) {
        if (addressRepository.findById(id).isEmpty()) {
            throw new ModelIdNotFoundException("Address", id);
        }

        addressRepository.deleteById(id);
    }

    public Address updateAddress(Address address, Long id) {
        Address updatedAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Address", id));

        if (address.getAddressName() != null && !address.getAddressName().isEmpty()) {
            updatedAddress.setAddressName(address.getAddressName());
        }

        for (Customer customer : address.getAddressCustomers()) {
            if (customer.getCustomerId() == null) {
                throw new ModelNonNullableFieldException("addressCustomers", "customerId");
            } else if (customer.getCustomerId() < 1L) {
                throw new ModelValueNotAllowed("addressCustomers", "customerId");
            }

            updatedAddress.getAddressCustomers().add(customerRepository.findById(customer.getCustomerId())
                    .orElseThrow(() -> new ModelIdNotFoundException("Customer", customer.getCustomerId())));

            customer.getCustomerAddresses().add(updatedAddress);
        }

        return addressRepository.save(updatedAddress);
    }
}
