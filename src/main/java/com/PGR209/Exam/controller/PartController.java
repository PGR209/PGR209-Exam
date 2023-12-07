package com.PGR209.Exam.controller;

import com.PGR209.Exam.model.Part;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/part")
public class PartController {
    @GetMapping("{id}")
    public Part getPartById(@PathVariable Integer id) {
        System.out.println("Part by id");
        return null;
    }

    @GetMapping
    public List<Part> getPartAll() {
        System.out.println("List of all parts");
        return new ArrayList<>();
    }

    @PostMapping
    public Part newPart(@RequestBody Part part) {
        System.out.println("Add new part");
        return part;
    }

    @DeleteMapping("{id}")
    public void deletePart(@PathVariable Integer id) {
        System.out.println("Delete part");
    }

    @PutMapping
    public Part updatePart(Part part) {
        System.out.println("Update part");
        return part;
    }
}
