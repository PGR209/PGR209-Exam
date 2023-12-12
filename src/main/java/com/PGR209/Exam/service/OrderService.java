package com.PGR209.Exam.service;

import com.PGR209.Exam.model.Order;
import com.PGR209.Exam.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

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
