package com.PGR209.Exam.service;

import com.PGR209.Exam.configuration.ApplicationConfiguration;
import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelNonNullableFieldException;
import com.PGR209.Exam.exception.ModelValueNotAllowed;
import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.model.Machine;
import com.PGR209.Exam.model.SalesOrder;
import com.PGR209.Exam.repository.AddressRepository;
import com.PGR209.Exam.repository.CustomerRepository;
import com.PGR209.Exam.repository.MachineRepository;
import com.PGR209.Exam.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesOrderService {
    private final SalesOrderRepository salesOrderRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final MachineRepository machineRepository;
    private final ApplicationConfiguration applicationConfiguration;

    @Autowired
    public SalesOrderService(SalesOrderRepository salesOrderRepository, CustomerRepository customerRepository, AddressRepository addressRepository, MachineRepository machineRepository, ApplicationConfiguration applicationConfiguration) {
        this.salesOrderRepository = salesOrderRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.machineRepository = machineRepository;
        this.applicationConfiguration = applicationConfiguration;
    }

    public SalesOrder getSalesOrderById(Long id) {
        return salesOrderRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("SalesOrder", id));
    }

    public List<SalesOrder> getSalesOrderPage(int page) {
        Pageable pageable = Pageable.ofSize(applicationConfiguration.getPageSize()).withPage(page);
        return salesOrderRepository.findAll(pageable).toList();
    }

    public SalesOrder newSalesOrder(SalesOrder salesOrder) {
        SalesOrder createdSalesOrder;
        Customer salesOrderCustomer;
        Address salesOrderAddress;
        List<Machine> salesOrderMachines = new ArrayList<>();

        if (salesOrder.getSalesOrderCustomer() == null) {
            throw new ModelNonNullableFieldException("SalesOrder", "salesOrderCustomer");
        } else {
            salesOrderCustomer = customerRepository.findById(salesOrder.getSalesOrderCustomer().getCustomerId())
                    .orElseThrow(() -> new ModelIdNotFoundException("Customer", salesOrder.getSalesOrderCustomer().getCustomerId()));
        }

        if (salesOrder.getSalesOrderAddress() == null) {
            throw new ModelNonNullableFieldException("SalesOrder", "salesOrderAddress");
        } else {
            salesOrderAddress = addressRepository.findById(salesOrder.getSalesOrderAddress().getAddressId())
                    .orElseThrow(() -> new ModelIdNotFoundException("Address", salesOrder.getSalesOrderAddress().getAddressId()));
        }

        for (Machine machine : salesOrder.getSalesOrderMachines()) {
            if (salesOrderMachines.stream().noneMatch(object -> object.getMachineId().equals(machine.getMachineId()))) {
                salesOrderMachines.add(machineRepository.findById(machine.getMachineId())
                        .orElseThrow(() -> new ModelValueNotAllowed("Machine", "machineId")));
            }
        }

        createdSalesOrder = new SalesOrder(
                salesOrderCustomer,
                salesOrderMachines,
                salesOrderAddress
        );

        if (customerRepository.findById(salesOrderCustomer.getCustomerId()).isPresent()) {
            customerRepository.findById(salesOrderCustomer.getCustomerId()).get().getCustomerSalesOrders().add(createdSalesOrder);
        }

        return salesOrderRepository.save(createdSalesOrder);
    }

    public void deleteSalesOrder(Long id) {
        if (salesOrderRepository.findById(id).isEmpty()) {
            throw new ModelIdNotFoundException("SalesOrder", id);
        }

        salesOrderRepository.deleteById(id);
    }

    public SalesOrder updateSalesOrder(SalesOrder salesOrder, Long id) {
        SalesOrder updatedSalesOrder = salesOrderRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("SalesOrder", id));

        //TEST WHILE GIVING NO ID
        if (salesOrder.getSalesOrderCustomer() != null) {
            updatedSalesOrder.setSalesOrderCustomer(customerRepository.findById(salesOrder.getSalesOrderCustomer().getCustomerId())
                    .orElseThrow(() -> new ModelNonNullableFieldException("salesOrderCustomer", "customerId")));
        }

        if (salesOrder.getSalesOrderAddress() != null) {
            updatedSalesOrder.setSalesOrderAddress(addressRepository.findById(salesOrder.getSalesOrderAddress().getAddressId())
                    .orElseThrow(() -> new ModelNonNullableFieldException("salesOrderAddress", "addressId")));
        }

        for (Machine machine : salesOrder.getSalesOrderMachines()) {
            if (machine.getMachineId() == null) {
                throw new ModelNonNullableFieldException("salesOrderMachines", "machineId");
            } else if (machine.getMachineId() < 1L) {
                throw new ModelValueNotAllowed("salesOrderMachines", "machineId");
            }

            if (updatedSalesOrder.getSalesOrderMachines().stream().noneMatch(object -> object.getMachineId().equals(machine.getMachineId()))) {
                updatedSalesOrder.getSalesOrderMachines().add(machineRepository.findById(machine.getMachineId())
                        .orElseThrow(() -> new ModelIdNotFoundException("Machine", machine.getMachineId())));
            }
        }

        return salesOrderRepository.save(updatedSalesOrder);
    }
}
