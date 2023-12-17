package com.PGR209.Exam.service;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

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
        return addressRepository.save(address);
    }

    public boolean deleteAddress(Long id) {
        boolean addressFound = addressRepository.findById(id).isPresent();

        if (addressFound) {
            addressRepository.deleteById(id);
        }

        return addressFound;
    }

    public Optional<Address> updateAddress(Address address, Long id) {
        Optional<Address> returnAddress = addressRepository.findById(id);

        if (returnAddress.isPresent()) {
            address.setId(id);

            returnAddress = Optional.of(addressRepository.save(address));
        }

        return returnAddress;
    }

    public Address addCustomer(Long id, Customer customer) {
        System.out.println("FIX ME");
        return null;
    }
}
