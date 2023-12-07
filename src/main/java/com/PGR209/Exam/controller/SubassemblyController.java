package com.PGR209.Exam.controller;

import com.PGR209.Exam.model.Subassembly;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class SubassemblyController {
    @GetMapping("{id}")
    public Subassembly getSubassemblyById() {
        System.out.println("Subassembly by id");
        return null;
    }

    @GetMapping
    public List<Subassembly> getSubassemblyAll() {
        System.out.println("List of all subassemblies");
        return new ArrayList<>();
    }

    @PostMapping
    public Subassembly newSubassembly(@RequestBody Subassembly subassembly) {
        System.out.println("Add new subassembly");
        return subassembly;
    }

    @DeleteMapping
    public void deleteSubassembly(Long id) {
        System.out.println("Delete subassembly");
    }

    @PutMapping
    public Subassembly updateSubassembly(Subassembly subassembly) {
        System.out.println("Update subassembly");
        return subassembly;
    }
}
