package com.PGR209.Exam.repository;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, PagingAndSortingRepository<Address, Long> {

}
