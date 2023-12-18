package com.PGR209.Exam.controller;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelListEmptyException;
import com.PGR209.Exam.exception.ModelValuesNotAllowed;
import com.PGR209.Exam.model.Subassembly;
import com.PGR209.Exam.service.SubassemblyService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return subassemblyService.getSubassemblyById(id);
    }

    @GetMapping("page/{pageNr}")
    public List<Subassembly> getSubassemblyPage(@PathVariable int pageNr, HttpServletResponse response) {
        List<Subassembly> subassemblies = subassemblyService.getSubassemblyPage(pageNr);

        if (subassemblies.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }

        return subassemblies;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public Subassembly newSubassembly(@RequestBody Subassembly subassembly) {
        return subassemblyService.newSubassembly(subassembly);
    }

    @DeleteMapping("{id}")
    public void deleteSubassembly(@PathVariable Long id) {
        subassemblyService.deleteSubassembly(id);
    }

    @PutMapping("{id}")
    public Subassembly updateSubassembly(@RequestBody Subassembly subassembly, @PathVariable Long id) {
        return subassemblyService.updateSubassembly(subassembly, id);
    }
}
