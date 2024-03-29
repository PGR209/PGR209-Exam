package com.PGR209.Exam.service;

import com.PGR209.Exam.configuration.ApplicationConfiguration;
import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelNonNullableFieldException;
import com.PGR209.Exam.exception.ModelValueNotAllowed;
import com.PGR209.Exam.model.Machine;
import com.PGR209.Exam.model.Subassembly;
import com.PGR209.Exam.repository.MachineRepository;
import com.PGR209.Exam.repository.SubassemblyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MachineService {
    private final MachineRepository machineRepository;
    private final SubassemblyRepository subassemblyRepository;
    private final ApplicationConfiguration applicationConfiguration;

    @Autowired
    public MachineService(MachineRepository machineRepository, SubassemblyRepository subassemblyRepository, ApplicationConfiguration applicationConfiguration) {
        this.machineRepository = machineRepository;
        this.subassemblyRepository = subassemblyRepository;
        this.applicationConfiguration = applicationConfiguration;
    }

    public Machine getMachineById(Long id) {
        return machineRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Machine", id));
    }

    public List<Machine> getMachinePage(int page) {
        Pageable pageable = Pageable.ofSize(applicationConfiguration.getPageSize()).withPage(page);
        return machineRepository.findAll(pageable).toList();
    }

    public Machine newMachine(Machine machine) {
        Machine createdMachine;
        List<Subassembly> machineSubassemblies = new ArrayList<>();

        if (machine.getMachineName() == null || machine.getMachineName().isEmpty()) {
            throw new ModelNonNullableFieldException("Machine", "machineName");
        }

        for (Subassembly subassembly : machine.getMachineSubassemblies()) {
            if (machineSubassemblies.stream().noneMatch(object -> object.getSubassemblyId().equals(subassembly.getSubassemblyId()))) {
                machineSubassemblies.add(subassemblyRepository.findById(subassembly.getSubassemblyId())
                        .orElseThrow(() -> new ModelValueNotAllowed("Subassembly", "subassemblyId")));
            }
        }

        createdMachine = new Machine(
                machine.getMachineName(),
                machine.getMachineQuantity(),
                machineSubassemblies
        );

        return machineRepository.save(createdMachine);
    }

    public void deleteMachine(Long id) {
        if (machineRepository.findById(id).isEmpty()) {
            throw new ModelIdNotFoundException("Machine", id);
        }

        machineRepository.deleteById(id);
    }

    public Machine updateMachine(Machine machine, Long id) {
        Machine updatedMachine = machineRepository.findById(id)
                .orElseThrow(() -> new ModelIdNotFoundException("Machine", id));

        if (machine.getMachineName() != null && !machine.getMachineName().isEmpty()) {
            updatedMachine.setMachineName(machine.getMachineName());
        }

        if (machine.getMachineQuantity() != 0) {
            updatedMachine.setMachineId(machine.getMachineId());
        }

        for (Subassembly subassembly : machine.getMachineSubassemblies()) {
            if (subassembly.getSubassemblyId() == null) {
                throw new ModelNonNullableFieldException("machineSubassemblies", "subassemblyId");
            } else if (subassembly.getSubassemblyId() < 1L) {
                throw new ModelValueNotAllowed("machineSubassemblies", "subassemblyId");
            }

            if (updatedMachine.getMachineSubassemblies().stream().noneMatch(object -> object.getSubassemblyId().equals(subassembly.getSubassemblyId()))) {
                updatedMachine.getMachineSubassemblies().add(subassemblyRepository.findById(subassembly.getSubassemblyId())
                        .orElseThrow(() -> new ModelIdNotFoundException("Subassembly", subassembly.getSubassemblyId())));
            }
        }

        return machineRepository.save(updatedMachine);
    }
}
