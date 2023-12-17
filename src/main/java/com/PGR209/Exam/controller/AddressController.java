package com.PGR209.Exam.controller;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelListEmptyException;
import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Address getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Address", id));
    }

    @GetMapping
    public List<Address> getAddressAll() {
        List<Address> addresses = addressService.getAddressAll();

        if (!addresses.isEmpty()) {
            return addresses;
        } else {
            throw new ModelListEmptyException("Address");
        }
    }

    @GetMapping("page/{pageNr}")
    public List<Address> getAddressPage(@PathVariable int pageNr) {
        List<Address> addresses = addressService.getAddressPage(pageNr);

        if (!addresses.isEmpty()) {
            return addresses;
        } else {
            throw new ModelListEmptyException("Address");
        }
    }

    @PostMapping
    public Address newAddress(@RequestBody Address address) {
        return addressService.newAddress(address);
    }

    @DeleteMapping("{id}")
    public void deleteAddress(@PathVariable Long id) {
        if (!addressService.deleteAddress(id)) {
            throw new ModelIdNotFoundException("Address", id);
        }
    }

    @PutMapping("{id}")
    public Address updateAddress(@RequestBody Address address, @PathVariable Long id) {
        return addressService.updateAddress(address, id)
                .orElseThrow(() -> new ModelIdNotFoundException("Address", id));
    }

    @PutMapping("{id}/customer")
    public Address addCustomer(@PathVariable Long id, Customer customer) {
        return addressService.addCustomer(id, customer);
    }
}
