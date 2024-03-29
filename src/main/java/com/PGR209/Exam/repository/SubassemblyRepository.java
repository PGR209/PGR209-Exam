package com.PGR209.Exam.repository;

import com.PGR209.Exam.model.Subassembly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubassemblyRepository extends JpaRepository<Subassembly, Long>, PagingAndSortingRepository<Subassembly, Long> {

}
