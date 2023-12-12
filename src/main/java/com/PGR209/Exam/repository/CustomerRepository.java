package com.PGR209.Exam.repository;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
