package com.PGR209.Exam.controller;

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
        return machineService.getMachineById(id);
    }

    @GetMapping
    public List<Machine> getMachineAll() {
        return machineService.getMachineAll();
    }

    @PostMapping
    public Machine newMachine(@RequestBody Machine machine) {
        return machineService.newMachine(machine);
    }

    @DeleteMapping("{id}")
    public void deleteMachine(@PathVariable Long id) {
        machineService.deleteMachine(id);
    }

    @PutMapping
    public Machine updateMachine(@RequestBody Machine machine) {
        return machineService.updateMachine(machine);
    }
}
