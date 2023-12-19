package com.PGR209.Exam.SubassemblyTests;

import com.PGR209.Exam.model.*;

import com.PGR209.Exam.repository.SubassemblyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SubassemblyRepositoryUnitTest {
    @Autowired
    SubassemblyRepository repository;

    @Test
    public void shouldNotFindSubassemblyIfDbTableEmpty(){
        Iterable<Subassembly> subassembly = repository.findAll();
        assertThat(subassembly).isEmpty();
    }

    @Test
    public void shouldAddASubassembly(){
        Subassembly subassembly = repository.save(new Subassembly(
                "SubassemblyRepoUnitTest",
                new ArrayList<>()));
        assertThat(subassembly.getSubassemblyName()).isEqualTo("SubassemblyRepoUnitTest");
    }

    @Test
    public void shouldUpdateExistingSubassembly(){
        Subassembly subassembly = repository.save(new Subassembly(
                "SubassemblyRepoUnitTest",
                new ArrayList<>()));
        subassembly.setSubassemblyName("NEWSubassemblyName");
        repository.save(subassembly);
        Long id = subassembly.getSubassemblyId();
        Subassembly updatedSubassembly = repository.findById(id).orElseThrow();
        assertThat(updatedSubassembly.getSubassemblyName()).isEqualTo("NEWSubassemblyName");
    }

    @Test
    public void shouldReadSubassembly(){
        Subassembly subassembly = repository.save(new Subassembly(
                "SubassemblyRepoUnitTest",
                new ArrayList<>()));
        Subassembly readSubassembly = repository.findById(subassembly.getSubassemblyId()).orElseThrow();
        assertThat(readSubassembly).isNotNull();
    }

    @Test
    public void shouldDeleteSubassembly(){
        Subassembly subassembly = repository.save(new Subassembly(
                "SubassemblyRepoUnitTest",
                new ArrayList<>()));
        Long id = subassembly.getSubassemblyId();
        repository.deleteById(id);
        assertThat(repository.findById(id)).isEmpty();
    }
}
