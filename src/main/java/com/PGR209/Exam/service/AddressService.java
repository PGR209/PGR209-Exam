package com.PGR209.Exam.service;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    public Address getAddressById(Integer id) {
        System.out.println("Get address by id " + id);
        return null;
    }

    public List<Address> getAddressAll() {
        System.out.println("Get all addresses");
        return new ArrayList<>();
    }

    public Address newAddress(Address address) {
        System.out.println("Create new address");
        return address;
    }

    public void deleteAddress(Integer id) {
        System.out.println("Delete address with id " + id);
    }

    public Address updateAddress(Address address) {
        System.out.println("Update address");
        return null;
    }

    public Address addCustomer(Integer id, Customer customer) {
        System.out.println("Add customer to address with id " + id);
        return null;
    }
}
