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

    public Subassembly getSubassemblyById(Integer id) {
        System.out.println("Get subassembly by id " + id);
        return null;
    }

    public List<Subassembly> getSubassemblyAll() {
        System.out.println("Get all subassemblies");
        return new ArrayList<>();
    }

    public Subassembly newSubassembly(Subassembly subassembly) {
        System.out.println("Create new subassembly");
        return subassembly;
    }

    public void deleteSubassembly(Integer id) {
        System.out.println("Delete subassembly with id " + id);
    }

    public Subassembly updateSubassembly(Subassembly subassembly) {
        System.out.println("Update subassembly");
        return null;
    }
}
