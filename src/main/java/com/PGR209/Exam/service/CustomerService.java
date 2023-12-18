package com.PGR209.Exam.service;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelNonNullableFieldException;
import com.PGR209.Exam.exception.ModelValueNotAllowed;
import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.model.SalesOrder;
import com.PGR209.Exam.repository.AddressRepository;
import com.PGR209.Exam.repository.CustomerRepository;
import com.PGR209.Exam.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final SalesOrderRepository salesOrderRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository, SalesOrderRepository salesOrderRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.salesOrderRepository = salesOrderRepository;
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Customer", id));
    }

    public List<Customer> getCustomerPage(int page) {
        //Move to config file?
        int pageSize = 4;

        Pageable pageable = Pageable.ofSize(pageSize).withPage(page);
        return customerRepository.findAll(pageable).toList();
    }

    public Customer newCustomer(Customer customer) {
        Customer createdCustomer;
        List<Address> customerAddresses = new ArrayList<>();
        List<SalesOrder> customerSalesOrders = new ArrayList<>();

        if (customer.getCustomerName() == null || customer.getCustomerName().isEmpty()) {
            throw new ModelNonNullableFieldException("Customer", "customerName");
        }

        if (customer.getCustomerEmail() == null || customer.getCustomerEmail().isEmpty()) {
            throw new ModelNonNullableFieldException("Customer", "customerEmail");
        }

        for (Address address : customer.getCustomerAddresses()) {
            customerAddresses.add(addressRepository.findById(address.getAddressId())
                    .orElseThrow(() -> new ModelValueNotAllowed("Address", "addressId")));
        }

        for (SalesOrder salesOrder : customer.getCustomerSalesOrders()) {
            customerSalesOrders.add(salesOrderRepository.findById(salesOrder.getSalesOrderId())
                    .orElseThrow(() -> new ModelValueNotAllowed("SalesOrder", "salesOrderId")));
        }

        createdCustomer = new Customer(
                customer.getCustomerName(),
                customer.getCustomerEmail(),
                customerAddresses,
                customerSalesOrders
        );

        return customerRepository.save(createdCustomer);
    }

    public void deleteCustomer(Long id) {
        if (customerRepository.findById(id).isEmpty()) {
            throw new ModelIdNotFoundException("Customer", id);
        }

        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Customer customer, Long id) {
        Optional<Customer> returnCustomer = customerRepository.findById(id);

        if (returnCustomer.isPresent()) {
            customer.setCustomerId(id);

            returnCustomer = Optional.of(customerRepository.save(customer));
        }

        return null;
    }

    public Customer addAddress(Long id, Address address) {
        List<Address> addresses;
        Customer updatedCustomer;

        updatedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Customer", id));

        addresses = updatedCustomer.getCustomerAddresses();
        addresses.add(addressRepository.findById(address.getAddressId())
                .orElseThrow(() -> new ModelIdNotFoundException("Address", id)));

        return customerRepository.save(updatedCustomer);
    }
}
