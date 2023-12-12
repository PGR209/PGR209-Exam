package com.PGR209.Exam.repository;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
