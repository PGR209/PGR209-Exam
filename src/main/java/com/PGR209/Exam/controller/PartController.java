package com.PGR209.Exam.controller;

import com.PGR209.Exam.model.Part;
import com.PGR209.Exam.service.PartService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return partService.getPartById(id);
    }

    @GetMapping("page/{pageNr}")
    public List<Part> getPartPage(@PathVariable int pageNr, HttpServletResponse response) {
        List<Part> parts = partService.getPartPage(pageNr);

        if (parts.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }

        return parts;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public Part newPart(@RequestBody Part part) {
        return partService.newPart(part);
    }

    @DeleteMapping("{id}")
    public void deletePart(@PathVariable Long id) {
        partService.deletePart(id);
    }

    @PutMapping("{id}")
    public Part updatePart(@RequestBody Part part, @PathVariable Long id) {
        return partService.updatePart(part, id);
    }
}
