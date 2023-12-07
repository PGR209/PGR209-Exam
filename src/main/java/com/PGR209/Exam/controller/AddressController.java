package com.PGR209.Exam.controller;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("{id}")
    public Address getAddressById(@PathVariable Integer id) {
        return addressService.getAddressById(id);
    }

    @GetMapping
    public List<Address> getAddressAll() {
        return addressService.getAddressAll();
    }

    @PostMapping
    public Address newAddress(@RequestBody Address address) {
        return addressService.newAddress(address);
    }

    @DeleteMapping("{id}")
    public void deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
    }

    @PutMapping
    public Address updateAddress(Address address) {
        return addressService.updateAddress(address);
    }

    @PutMapping("{id}")
    public Address addCustomer(@PathVariable Integer id, Customer customer) {
        return addressService.addCustomer(id, customer);
    }
}
