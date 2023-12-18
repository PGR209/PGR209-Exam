package com.PGR209.Exam.service;

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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalesOrderService {
    private final SalesOrderRepository salesOrderRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final MachineRepository machineRepository;

    @Autowired
    public SalesOrderService(SalesOrderRepository salesOrderRepository, CustomerRepository customerRepository, AddressRepository addressRepository, MachineRepository machineRepository) {
        this.salesOrderRepository = salesOrderRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.machineRepository = machineRepository;
    }

    public SalesOrder getSalesOrderById(Long id) {
        return salesOrderRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("SalesOrder", id));
    }

    public List<SalesOrder> getSalesOrderPage(int page) {
        //Move to config file?
        int pageSize = 4;

        Pageable pageable = Pageable.ofSize(pageSize).withPage(page);
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
            salesOrderMachines.add(machineRepository.findById(machine.getMachineId())
                    .orElseThrow(() -> new ModelValueNotAllowed("Machine", "machineId")));
        }

        createdSalesOrder = new SalesOrder(
                salesOrderCustomer,
                salesOrderMachines,
                salesOrderAddress
        );

        return salesOrderRepository.save(createdSalesOrder);
    }

    public void deleteSalesOrder(Long id) {
        if (salesOrderRepository.findById(id).isEmpty()) {
            throw new ModelIdNotFoundException("SalesOrder", id);
        }

        salesOrderRepository.deleteById(id);
    }

    public SalesOrder updateSalesOrder(SalesOrder salesOrder, Long id) {
        Optional<SalesOrder> returnSalesOrder = salesOrderRepository.findById(id);

        if (returnSalesOrder.isPresent()) {
            salesOrder.setSalesOrderId(id);

            returnSalesOrder = Optional.of(salesOrderRepository.save(salesOrder));
        }

        return null;
    }
}
