package com.PGR209.Exam.controller;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelListEmptyException;
import com.PGR209.Exam.model.Subassembly;
import com.PGR209.Exam.service.SubassemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Subassembly getSubassemblyById(@PathVariable Long id) {
        return subassemblyService.getSubassemblyById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Subassembly", id));
    }

    @GetMapping
    public List<Subassembly> getSubassemblyAll() {
        List<Subassembly> subassemblies = subassemblyService.getSubassemblyAll();

        if (!subassemblies.isEmpty()) {
            return subassemblies;
        } else {
            throw new ModelListEmptyException("Subassembly");
        }
    }

    @PostMapping
    public Subassembly newSubassembly(@RequestBody Subassembly subassembly) {
        return subassemblyService.newSubassembly(subassembly);
    }

    @DeleteMapping("{id}")
    public void deleteSubassembly(@PathVariable Long id) {
        subassemblyService.deleteSubassembly(id);
    }

    @PutMapping
    public Subassembly updateSubassembly(@RequestBody Subassembly subassembly) {
        return subassemblyService.updateSubassembly(subassembly);
    }
}
