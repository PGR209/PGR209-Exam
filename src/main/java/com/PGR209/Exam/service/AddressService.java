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
public class AddressService {
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
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

    public Optional<Address> newAddress(Address address) {
        try {
            return Optional.of(addressRepository.save(address));
        } catch (DataIntegrityViolationException error) {
            return Optional.empty();
        }
    }

    public boolean deleteAddress(Long id) {
        if (addressRepository.findById(id).isPresent()) {
            addressRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public Optional<Address> updateAddress(Address address, Long id) {
        Optional<Address> returnAddress = addressRepository.findById(id);

        if (returnAddress.isPresent()) {
            address.setAddressId(id);

            returnAddress = Optional.of(addressRepository.save(address));
        }

        return returnAddress;
    }
}
