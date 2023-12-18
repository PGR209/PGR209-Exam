package com.PGR209.Exam.service;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.model.Subassembly;
import com.PGR209.Exam.repository.SubassemblyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubassemblyService {
    private final SubassemblyRepository subassemblyRepository;

    @Autowired
    public SubassemblyService(SubassemblyRepository subassemblyRepository) {
        this.subassemblyRepository = subassemblyRepository;
    }

    public Subassembly getSubassemblyById(Long id) {
        return subassemblyRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Subassembly", id));
    }

    public List<Subassembly> getSubassemblyPage(int page) {
        //Move to config file?
        int pageSize = 4;

        Pageable pageable = Pageable.ofSize(pageSize).withPage(page);
        return subassemblyRepository.findAll(pageable).toList();
    }

    public Optional<Subassembly> newSubassembly(Subassembly subassembly) {
        try {
            return Optional.of(subassemblyRepository.save(subassembly));
        } catch (DataIntegrityViolationException error) {
            return Optional.empty();
        }
    }

    public void deleteSubassembly(Long id) {
        if (subassemblyRepository.findById(id).isEmpty()) {
            throw new ModelIdNotFoundException("Subassembly", id);
        }

        subassemblyRepository.deleteById(id);
    }

    public Optional<Subassembly> updateSubassembly(Subassembly subassembly, Long id) {
        Optional<Subassembly> returnSubassembly = subassemblyRepository.findById(id);

        if (returnSubassembly.isPresent()) {
            subassembly.setSubassemblyId(id);

            returnSubassembly = Optional.of(subassemblyRepository.save(subassembly));
        }

        return returnSubassembly;
    }
}
