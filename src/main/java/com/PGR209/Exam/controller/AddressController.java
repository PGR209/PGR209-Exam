package com.PGR209.Exam.controller;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressController {
    @GetMapping("{id}")
    public Address getAddressById() {
        System.out.println("Address by id");
        return null;
    }

    @GetMapping
    public List<Address> getAddressAll() {
        System.out.println("List of all addresses");
        return new ArrayList<>();
    }

    @PostMapping
    public Address newAddress(@RequestBody Address address) {
        System.out.println("Add new address");
        return address;
    }

    @DeleteMapping
    public void deleteAddress(Long id) {
        System.out.println("Delete address");
    }

    @PutMapping
    public Address updateAddress(Address address) {
        System.out.println("Update address");
        return address;
    }

    @PutMapping
    public Address addCustomer(Long id, Customer customer) {
        System.out.println("Add customer to address");
        return null;
    }
}
