package com.PGR209.Exam.service;

import com.PGR209.Exam.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    public Order getOrderById(Integer id) {
        System.out.println("Get order by id " + id);
        return null;
    }

    public List<Order> getOrderAll() {
        System.out.println("Get all orders");
        return new ArrayList<>();
    }

    public Order newOrder(Order order) {
        System.out.println("Create new order");
        return order;
    }

    public void deleteOrder(Integer id) {
        System.out.println("Delete order with id " + id);
    }

    public Order updateOrder(Order order) {
        System.out.println("Update order");
        return null;
    }
}
