package com.PGR209.Exam.repository;

import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long>, PagingAndSortingRepository<Part, Long> {

}
