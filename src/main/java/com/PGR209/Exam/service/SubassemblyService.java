package com.PGR209.Exam.service;

import com.PGR209.Exam.model.Subassembly;
import com.PGR209.Exam.repository.SubassemblyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubassemblyService {
    private final SubassemblyRepository subassemblyRepository;

    @Autowired
    public SubassemblyService(SubassemblyRepository subassemblyRepository) {
        this.subassemblyRepository = subassemblyRepository;
    }

    public Subassembly getSubassemblyById(Long id) {
        return subassemblyRepository.findById(id).orElse(null);
    }

    public List<Subassembly> getSubassemblyAll() {
        return subassemblyRepository.findAll();
    }

    public Subassembly newSubassembly(Subassembly subassembly) {
        return subassemblyRepository.save(subassembly);
    }

    public void deleteSubassembly(Long id) {
        subassemblyRepository.deleteById(id);
    }

    public Subassembly updateSubassembly(Subassembly subassembly) {
        return subassemblyRepository.save(subassembly);
    }
}
