package com.PGR209.Exam.controller;

import com.PGR209.Exam.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    @GetMapping("{id}")
    public Order getOrderById(@PathVariable Integer id) {
        System.out.println("Order by id");
        return null;
    }

    @GetMapping
    public List<Order> getOrderAll() {
        System.out.println("List of all orders");
        return new ArrayList<>();
    }

    @PostMapping
    public Order newOrder(@RequestBody Order order) {
        System.out.println("Add new order");
        return order;
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable Integer id) {
        System.out.println("Delete order");
    }

    @PutMapping
    public Order updateOrder(Order order) {
        System.out.println("Update order");
        return order;
    }
}
