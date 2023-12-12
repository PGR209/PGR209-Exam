package com.PGR209.Exam.service;

import com.PGR209.Exam.model.Machine;
import com.PGR209.Exam.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MachineService {
    private final MachineRepository machineRepository;

    @Autowired
    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }
    public Machine getMachineById(Integer id) {
        System.out.println("Get machine by id " + id);
        return null;
    }

    public List<Machine> getMachineAll() {
        System.out.println("Get all machines");
        return new ArrayList<>();
    }

    public Machine newMachine(Machine machine) {
        System.out.println("Create new machine");
        return machine;
    }

    public void deleteMachine(Integer id) {
        System.out.println("Delete machine with id " + id);
    }

    public Machine updateMachine(Machine machine) {
        System.out.println("Update machine");
        return null;
    }
}
