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

    public Part getPartById(Integer id) {
        System.out.println("Get part by id " + id);
        return null;
    }

    public List<Part> getPartAll() {
        System.out.println("Get all parts");
        return new ArrayList<>();
    }

    public Part newPart(Part part) {
        System.out.println("Create new part");
        return part;
    }

    public void deletePart(Integer id) {
        System.out.println("Delete part with id " + id);
    }

    public Part updatePart(Part part) {
        System.out.println("Update part");
        return null;
    }
}
