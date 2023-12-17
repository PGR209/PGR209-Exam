package com.PGR209.Exam.controller;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelListEmptyException;
import com.PGR209.Exam.exception.ModelValuesNotAllowed;
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
    public Part getPartById(@PathVariable Long id) {
        return partService.getPartById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Part", id));
    }

    @GetMapping
    public List<Part> getPartAll() {
        List<Part> parts = partService.getPartAll();

        if (!parts.isEmpty()) {
            return parts;
        } else {
            throw new ModelListEmptyException("Part");
        }
    }

    @GetMapping("page/{pageNr}")
    public List<Part> getPartPage(@PathVariable int pageNr) {
        List<Part> parts = partService.getPartPage(pageNr);

        if (!parts.isEmpty()) {
            return parts;
        } else {
            throw new ModelListEmptyException("Part");
        }
    }

    @PostMapping
    public Part newPart(@RequestBody Part part) {
        return partService.newPart(part)
                .orElseThrow(() -> new ModelValuesNotAllowed("Part"));
    }

    @DeleteMapping("{id}")
    public void deletePart(@PathVariable Long id) {
        if (!partService.deletePart(id)) {
            throw new ModelIdNotFoundException("Part", id);
        }
    }

    @PutMapping("{id}")
    public Part updatePart(@RequestBody Part part, @PathVariable Long id) {
        return partService.updatePart(part, id)
                .orElseThrow(() -> new ModelIdNotFoundException("Part", id));
    }
}
