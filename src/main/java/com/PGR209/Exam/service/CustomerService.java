package com.PGR209.Exam.service;

import com.PGR209.Exam.configuration.ApplicationConfiguration;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final SalesOrderRepository salesOrderRepository;
    private final ApplicationConfiguration applicationConfiguration;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository, SalesOrderRepository salesOrderRepository, ApplicationConfiguration applicationConfiguration) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.salesOrderRepository = salesOrderRepository;
        this.applicationConfiguration = applicationConfiguration;
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Customer", id));
    }

    public List<Customer> getCustomerPage(int page) {
        Pageable pageable = Pageable.ofSize(applicationConfiguration.getPageSize()).withPage(page);
        return customerRepository.findAll(pageable).toList();
    }

    public Customer newCustomer(Customer customer) {
        Customer createdCustomer;
        List<Address> customerAddresses = new ArrayList<>();

        if (customer.getCustomerName() == null || customer.getCustomerName().isEmpty()) {
            throw new ModelNonNullableFieldException("Customer", "customerName");
        }

        if (customer.getCustomerEmail() == null || customer.getCustomerEmail().isEmpty()) {
            throw new ModelNonNullableFieldException("Customer", "customerEmail");
        }

        for (Address address : customer.getCustomerAddresses()) {
            if (customerAddresses.stream().noneMatch(object -> object.getAddressId().equals(address.getAddressId()))) {
                customerAddresses.add(addressRepository.findById(address.getAddressId())
                        .orElseThrow(() -> new ModelValueNotAllowed("Address", "addressId")));
            }
        }

        createdCustomer = new Customer(
                customer.getCustomerName(),
                customer.getCustomerEmail(),
                customerAddresses,
                new ArrayList<>()
        );

        for (Address address : createdCustomer.getCustomerAddresses()) {
            address.getAddressCustomers().add(createdCustomer);
        }

        return customerRepository.save(createdCustomer);
    }

    public void deleteCustomer(Long id) {
        if (customerRepository.findById(id).isEmpty()) {
            throw new ModelIdNotFoundException("Customer", id);
        }

        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Customer customer, Long id) {
        Customer updatedCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Customer", id));

        if (customer.getCustomerName() != null && !customer.getCustomerName().isEmpty()) {
            updatedCustomer.setCustomerName(customer.getCustomerName());
        }

        if (customer.getCustomerEmail() != null && !customer.getCustomerEmail().isEmpty()) {
            updatedCustomer.setCustomerEmail(customer.getCustomerEmail());
        }

        for (Address address : customer.getCustomerAddresses()) {
            if (address.getAddressId() == null) {
                throw new ModelNonNullableFieldException("customerAddresses", "addressId");
            } else if (address.getAddressId() < 1L) {
                throw new ModelValueNotAllowed("customerAddresses", "addressId");
            }

            if (updatedCustomer.getCustomerAddresses().stream().noneMatch(object -> object.getAddressId().equals(address.getAddressId()))) {
                updatedCustomer.getCustomerAddresses().add(addressRepository.findById(address.getAddressId())
                        .orElseThrow(() -> new ModelIdNotFoundException("Address", address.getAddressId())));
            }
        }

        for (SalesOrder salesOrder : customer.getCustomerSalesOrders()) {
            if (salesOrder.getSalesOrderId() == null) {
                throw new ModelNonNullableFieldException("customerSalesOrders", "salesOrderId");
            } else if (salesOrder.getSalesOrderId() < 1L) {
                throw new ModelValueNotAllowed("customerSalesOrders", "salesOrderId");
            }

            if (updatedCustomer.getCustomerSalesOrders().stream().noneMatch(object -> object.getSalesOrderId().equals(salesOrder.getSalesOrderId()))) {
                updatedCustomer.getCustomerSalesOrders().add(salesOrderRepository.findById(salesOrder.getSalesOrderId())
                        .orElseThrow(() -> new ModelIdNotFoundException("SalesOrder", salesOrder.getSalesOrderId())));
            }
        }

        return customerRepository.save(updatedCustomer);
    }
}
