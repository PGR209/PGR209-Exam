package com.PGR209.Exam.PartTests;

import com.PGR209.Exam.model.*;

import com.PGR209.Exam.repository.PartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PartRepositoryUnitTest {
    @Autowired
    PartRepository repository;

    @Test
    public void shouldNotFindPartIfDbTableEmpty(){
        Iterable<Part> part = repository.findAll();
        assertThat(part).isEmpty();
    }

    @Test
    public void shouldAddAPart(){
        Part part = repository.save(new Part("PartRepoUnitTest"));
        assertThat(part.getPartName()).isEqualTo("PartRepoUnitTest");
    }

    @Test
    public void shouldUpdateExistingPart(){
        Part part = repository.save(new Part("PartRepoUnitTest"));
        part.setPartName("NEWPartName");
        repository.save(part);
        Long id = part.getPartId();
        Part updatedPart = repository.findById(id).orElseThrow();
        assertThat(updatedPart.getPartName()).isEqualTo("NEWPartName");
    }

    @Test
    public void shouldReadPart(){
        Part part = repository.save(new Part("PartRepoUnitTest"));
        Part readPart = repository.findById(part.getPartId()).orElseThrow();
        assertThat(readPart).isNotNull();
    }

    @Test
    public void shouldDeletePart(){
        Part part = repository.save(new Part("PartRepoUnitTest"));
        Long id = part.getPartId();
        repository.deleteById(id);
        assertThat(repository.findById(id)).isEmpty();
    }
}
