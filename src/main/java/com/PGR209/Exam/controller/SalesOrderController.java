package com.PGR209.Exam.controller;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelListEmptyException;
import com.PGR209.Exam.exception.ModelValuesNotAllowed;
import com.PGR209.Exam.model.SalesOrder;
import com.PGR209.Exam.service.SalesOrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<SalesOrder> getSalesOrderAll(HttpServletResponse response) {
        List<SalesOrder> salesOrders = salesOrderService.getSalesOrderAll();

        if (salesOrders.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }

        return salesOrders;
    }

    @GetMapping("page/{pageNr}")
    public List<SalesOrder> getSalesOrderPage(@PathVariable int pageNr, HttpServletResponse response) {
        List<SalesOrder> salesOrders = salesOrderService.getSalesOrderPage(pageNr);

        if (salesOrders.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }

        return salesOrders;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public SalesOrder newSalesOrder(@RequestBody SalesOrder salesOrder) {
        return salesOrderService.newSalesOrder(salesOrder);
    }

    @DeleteMapping("{id}")
    public void deleteSalesOrder(@PathVariable Long id) {
        salesOrderService.deleteSalesOrder(id);
    }

    @PutMapping("{id}")
    public SalesOrder updateSalesOrder(@RequestBody SalesOrder salesOrder, @PathVariable Long id) {
        return salesOrderService.updateSalesOrder(salesOrder, id);
    }
}
