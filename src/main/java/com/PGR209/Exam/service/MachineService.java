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

    public Machine getMachineById(Long id) {
        return machineRepository.findById(id).orElse(null);
    }

    public List<Machine> getMachineAll() {
        return machineRepository.findAll();
    }

    public Machine newMachine(Machine machine) {
        return machineRepository.save(machine);
    }

    public void deleteMachine(Long id) {
        machineRepository.deleteById(id);
    }

    public Machine updateMachine(Machine machine) {
        return machineRepository.save(machine);
    }
}
