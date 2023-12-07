package com.PGR209.Exam.controller;

import com.PGR209.Exam.model.Part;
import com.PGR209.Exam.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/part")
public class PartController {
    private final PartService partService;

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("{id}")
    public Part getPartById(@PathVariable Integer id) {
        return partService.getPartById(id);
    }

    @GetMapping
    public List<Part> getPartAll() {
        return partService.getPartAll();
    }

    @PostMapping
    public Part newPart(@RequestBody Part part) {
        return partService.newPart(part);
    }

    @DeleteMapping("{id}")
    public void deletePart(@PathVariable Integer id) {
        partService.deletePart(id);
    }

    @PutMapping
    public Part updatePart(Part part) {
        return partService.updatePart(part);
    }
}
