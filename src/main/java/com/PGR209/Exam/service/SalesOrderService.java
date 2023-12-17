package com.PGR209.Exam.service;

import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.model.SalesOrder;
import com.PGR209.Exam.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesOrderService {
    private final SalesOrderRepository salesOrderRepository;

    @Autowired
    public SalesOrderService(SalesOrderRepository salesOrderRepository) {
        this.salesOrderRepository = salesOrderRepository;
    }

    public Optional<SalesOrder> getSalesOrderById(Long id) {
        return salesOrderRepository.findById(id);
    }

    public List<SalesOrder> getSalesOrderAll() { return salesOrderRepository.findAll(); }

    public List<SalesOrder> getSalesOrderPage(int page) {
        //Move to config file?
        int pageSize = 4;

        Pageable pageable = Pageable.ofSize(pageSize).withPage(page);
        return salesOrderRepository.findAll(pageable).toList();
    }

    public SalesOrder newSalesOrder(SalesOrder salesOrder) {
        return salesOrderRepository.save(salesOrder);
    }

    public void deleteSalesOrder(Long id) {
        salesOrderRepository.deleteById(id);
    }

    public Optional<SalesOrder> updateSalesOrder(SalesOrder salesOrder, Long id) {
        Optional<SalesOrder> returnSalesOrder = salesOrderRepository.findById(id);

        if (returnSalesOrder.isPresent()) {
            salesOrder.setId(id);

            returnSalesOrder = Optional.of(salesOrderRepository.save(salesOrder));
        }

        return returnSalesOrder;
    }
}
