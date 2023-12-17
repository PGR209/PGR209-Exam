package com.PGR209.Exam.service;

import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.model.Machine;
import com.PGR209.Exam.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {
    private final MachineRepository machineRepository;

    @Autowired
    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public Optional<Machine> getMachineById(Long id) {
        return machineRepository.findById(id);
    }

    public List<Machine> getMachineAll() {
        return machineRepository.findAll();
    }

    public List<Machine> getMachinePage(int page) {
        //Move to config file?
        int pageSize = 4;

        Pageable pageable = Pageable.ofSize(pageSize).withPage(page);
        return machineRepository.findAll(pageable).toList();
    }

    public Machine newMachine(Machine machine) {
        return machineRepository.save(machine);
    }

    public void deleteMachine(Long id) {
        machineRepository.deleteById(id);
    }

    public Optional<Machine> updateMachine(Machine machine, Long id) {
        Optional<Machine> returnMachine = machineRepository.findById(id);

        if (returnMachine.isPresent()) {
            machine.setId(id);

            returnMachine = Optional.of(machineRepository.save(machine));
        }

        return returnMachine;
    }
}
