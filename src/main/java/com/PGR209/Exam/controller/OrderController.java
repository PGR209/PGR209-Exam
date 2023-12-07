package com.PGR209.Exam.controller;

import com.PGR209.Exam.model.Order;
import com.PGR209.Exam.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{id}")
    public Order getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<Order> getOrderAll() {
        return orderService.getOrderAll();
    }

    @PostMapping
    public Order newOrder(@RequestBody Order order) {
        return orderService.newOrder(order);
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }

    @PutMapping
    public Order updateOrder(Order order) {
        return orderService.updateOrder(order);
    }
}
