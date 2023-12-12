package com.PGR209.Exam.service;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> getAddressAll() {
        return addressRepository.findAll();
    }

    public Address newAddress(Address address) {
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public Address updateAddress(Address address) {
        System.out.println("FIX ME");
        return null;
    }

    public Address addCustomer(Long id, Customer customer) {
        System.out.println("FIX ME");
        return null;
    }
}
