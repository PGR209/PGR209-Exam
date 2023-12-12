package com.PGR209.Exam.controller;

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
        return salesOrderService.getSalesOrderById(id);
    }

    @GetMapping
    public List<SalesOrder> getSalesOrderAll() {
        return salesOrderService.getSalesOrderAll();
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
    public SalesOrder updateSalesOrder(SalesOrder salesOrder) {
        return salesOrderService.updateSalesOrder(salesOrder);
    }
}
