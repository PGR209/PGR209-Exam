package com.PGR209.Exam.repository;

import com.PGR209.Exam.model.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long>, PagingAndSortingRepository<SalesOrder, Long> {

}
