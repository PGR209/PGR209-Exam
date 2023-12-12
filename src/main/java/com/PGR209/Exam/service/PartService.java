package com.PGR209.Exam.service;

import com.PGR209.Exam.model.Part;
import com.PGR209.Exam.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartService {
    private final PartRepository partRepository;

    @Autowired
    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public Part getPartById(Long id) {
        return partRepository.findById(id).orElse(null);
    }

    public List<Part> getPartAll() {
        return partRepository.findAll();
    }

    public Part newPart(Part part) {
        return partRepository.save(part);
    }

    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }

    public Part updatePart(Part part) {
        return partRepository.save(part);
    }
}
