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
    public Address getAddressById(@PathVariable Integer id) {
        System.out.println("Address by id " + id);
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

    @DeleteMapping("{id}")
    public void deleteAddress(@PathVariable Integer id) {
        System.out.println("Delete address with id " + id);
    }

    @PutMapping
    public Address updateAddress(Address address) {
        System.out.println("Update address");
        return address;
    }

    @PutMapping("{id}")
    public Address addCustomer(@PathVariable Integer id, Customer customer) {
        System.out.println("Add address to customer with id " + id);
        return null;
    }
}
