package com.PGR209.Exam.controller;

import com.PGR209.Exam.model.Machine;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/machine")
public class MachineController {
    @GetMapping("{id}")
    public Machine getMachineById(@PathVariable Integer id) {
        System.out.println("Machine by id");
        return null;
    }

    @GetMapping
    public List<Machine> getMachineAll() {
        System.out.println("List of all machines");
        return new ArrayList<>();
    }

    @PostMapping
    public Machine newMachine(@RequestBody Machine machine) {
        System.out.println("Add new machine");
        return machine;
    }

    @DeleteMapping("{id}")
    public void deleteMachine(@PathVariable Integer id) {
        System.out.println("Delete machine");
    }

    @PutMapping
    public Machine updateMachine(Machine machine) {
        System.out.println("Update machine");
        return machine;
    }
}
