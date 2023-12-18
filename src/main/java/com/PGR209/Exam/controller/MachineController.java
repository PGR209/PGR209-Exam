package com.PGR209.Exam.controller;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelListEmptyException;
import com.PGR209.Exam.exception.ModelValuesNotAllowed;
import com.PGR209.Exam.model.Machine;
import com.PGR209.Exam.service.MachineService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/machine")
public class MachineController {
    private final MachineService machineService;

    @Autowired
    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @GetMapping("{id}")
    public Machine getMachineById(@PathVariable Long id) {
        return machineService.getMachineById(id);
    }

    @GetMapping
    public List<Machine> getMachineAll(HttpServletResponse response) {
        List<Machine> machines = machineService.getMachineAll();

        if (machines.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }

        return machines;
    }

    @GetMapping("page/{pageNr}")
    public List<Machine> getMachinePage(@PathVariable int pageNr, HttpServletResponse response) {
        List<Machine> machines = machineService.getMachinePage(pageNr);

        if (machines.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }

        return machines;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public Machine newMachine(@RequestBody Machine machine) {
        return machineService.newMachine(machine);
    }

    @DeleteMapping("{id}")
    public void deleteMachine(@PathVariable Long id) {
        machineService.deleteMachine(id);
    }

    @PutMapping("{id}")
    public Machine updateMachine(@RequestBody Machine machine, @PathVariable Long id) {
        return machineService.updateMachine(machine, id);
    }
}
