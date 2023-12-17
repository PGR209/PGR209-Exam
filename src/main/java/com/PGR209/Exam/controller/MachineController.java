package com.PGR209.Exam.controller;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelListEmptyException;
import com.PGR209.Exam.exception.ModelValuesNotAllowed;
import com.PGR209.Exam.model.Machine;
import com.PGR209.Exam.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return machineService.getMachineById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Machine", id));
    }

    @GetMapping
    public List<Machine> getMachineAll() {
        List<Machine> machines = machineService.getMachineAll();

        if (!machines.isEmpty()) {
            return machines;
        } else {
            throw new ModelListEmptyException("Machine");
        }
    }

    @GetMapping("page/{pageNr}")
    public List<Machine> getMachinePage(@PathVariable int pageNr) {
        List<Machine> machines = machineService.getMachinePage(pageNr);

        if (!machines.isEmpty()) {
            return machines;
        } else {
            throw new ModelListEmptyException("Machine");
        }
    }

    @PostMapping
    public Machine newMachine(@RequestBody Machine machine) {
        return machineService.newMachine(machine)
                .orElseThrow(() -> new ModelValuesNotAllowed("Machine"));
    }

    @DeleteMapping("{id}")
    public void deleteMachine(@PathVariable Long id) {
        if (!machineService.deleteMachine(id)) {
            throw new ModelIdNotFoundException("Machine", id);
        }
    }

    @PutMapping("{id}")
    public Machine updateMachine(@RequestBody Machine machine, @PathVariable Long id) {
        return machineService.updateMachine(machine, id)
                .orElseThrow(() -> new ModelIdNotFoundException("Machine", id));
    }
}
