package com.PGR209.Exam.controller;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelListEmptyException;
import com.PGR209.Exam.model.SalesOrder;
import com.PGR209.Exam.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/salesorder")
public class SalesOrderController {
    private final SalesOrderService salesOrderService;

    @Autowired
    public SalesOrderController(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }

    @GetMapping("{id}")
    public SalesOrder getSalesOrderById(@PathVariable Long id) {
        return salesOrderService.getSalesOrderById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("SalesOrder", id));
    }

    @GetMapping
    public List<SalesOrder> getSalesOrderAll() {
        List<SalesOrder> salesOrders = salesOrderService.getSalesOrderAll();

        if (!salesOrders.isEmpty()) {
            return salesOrders;
        } else {
            throw new ModelListEmptyException("SalesOrder");
        }
    }

    @PostMapping
    public SalesOrder newSalesOrder(@RequestBody SalesOrder salesOrder) {
        return salesOrderService.newSalesOrder(salesOrder);
    }

    @DeleteMapping("{id}")
    public void deleteSalesOrder(@PathVariable Long id) {
        salesOrderService.deleteSalesOrder(id);
    }

    @PutMapping
    public SalesOrder updateSalesOrder(@RequestBody SalesOrder salesOrder) {
        return salesOrderService.updateSalesOrder(salesOrder);
    }
}
