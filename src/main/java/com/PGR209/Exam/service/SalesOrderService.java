package com.PGR209.Exam.service;

import com.PGR209.Exam.model.SalesOrder;
import com.PGR209.Exam.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<SalesOrder> getSalesOrderAll() {
        return salesOrderRepository.findAll();
    }

    public SalesOrder newSalesOrder(SalesOrder salesOrder) {
        return salesOrderRepository.save(salesOrder);
    }

    public void deleteSalesOrder(Long id) {
        salesOrderRepository.deleteById(id);
    }

    public SalesOrder updateSalesOrder(SalesOrder salesOrder) {
        return salesOrderRepository.save(salesOrder);
    }
}
