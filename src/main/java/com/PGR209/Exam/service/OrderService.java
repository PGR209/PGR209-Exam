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

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrderAll() {
        return orderRepository.findAll();
    }

    public Order newOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrder(Order order) {
        System.out.println("FIX ME");
        return null;
    }
}
