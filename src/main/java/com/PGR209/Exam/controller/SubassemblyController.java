package com.PGR209.Exam.controller;

import com.PGR209.Exam.model.Subassembly;
import com.PGR209.Exam.service.SubassemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/subassembly")
public class SubassemblyController {
    private final SubassemblyService subassemblyService;

    @Autowired
    public SubassemblyController(SubassemblyService subassemblyService) {
        this.subassemblyService = subassemblyService;
    }

    @GetMapping("{id}")
    public Subassembly getSubassemblyById(@PathVariable Integer id) {
        return subassemblyService.getSubassemblyById(id);
    }

    @GetMapping
    public List<Subassembly> getSubassemblyAll() {
        return subassemblyService.getSubassemblyAll();
    }

    @PostMapping
    public Subassembly newSubassembly(@RequestBody Subassembly subassembly) {
        return subassemblyService.newSubassembly(subassembly);
    }

    @DeleteMapping("{id}")
    public void deleteSubassembly(@PathVariable Integer id) {
        subassemblyService.deleteSubassembly(id);
    }

    @PutMapping
    public Subassembly updateSubassembly(Subassembly subassembly) {
        return subassemblyService.updateSubassembly(subassembly);
    }
}
