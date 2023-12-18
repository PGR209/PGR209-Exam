package com.PGR209.Exam.service;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.model.Machine;
import com.PGR209.Exam.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MachineService {
    private final MachineRepository machineRepository;

    @Autowired
    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public Machine getMachineById(Long id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Machine", id));
    }

    public List<Machine> getMachinePage(int page) {
        //Move to config file?
        int pageSize = 4;

        Pageable pageable = Pageable.ofSize(pageSize).withPage(page);
        return machineRepository.findAll(pageable).toList();
    }

    public Optional<Machine> newMachine(Machine machine) {
        try {
            return Optional.of(machineRepository.save(machine));
        } catch (DataIntegrityViolationException error) {
            return Optional.empty();
        }
    }

    public void deleteMachine(Long id) {
        if (machineRepository.findById(id).isEmpty()) {
            throw new ModelIdNotFoundException("Machine", id);
        }

        machineRepository.deleteById(id);
    }

    public Optional<Machine> updateMachine(Machine machine, Long id) {
        Optional<Machine> returnMachine = machineRepository.findById(id);

        if (returnMachine.isPresent()) {
            machine.setMachineId(id);

            returnMachine = Optional.of(machineRepository.save(machine));
        }

        return returnMachine;
    }
}
