package com.PGR209.Exam.service;

import com.PGR209.Exam.exception.ModelIdNotFoundException;
import com.PGR209.Exam.exception.ModelNonNullableFieldException;
import com.PGR209.Exam.exception.ModelValueNotAllowed;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.model.Machine;
import com.PGR209.Exam.model.Subassembly;
import com.PGR209.Exam.repository.MachineRepository;
import com.PGR209.Exam.repository.SubassemblyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MachineService {
    private final MachineRepository machineRepository;
    private final SubassemblyRepository subassemblyRepository;

    @Autowired
    public MachineService(MachineRepository machineRepository, SubassemblyRepository subassemblyRepository) {
        this.machineRepository = machineRepository;
        this.subassemblyRepository = subassemblyRepository;
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

    public Machine newMachine(Machine machine) {
        Machine createdMachine;
        List<Subassembly> machineSubassemblies = new ArrayList<>();

        if (machine.getMachineName() == null || machine.getMachineName().isEmpty()) {
            throw new ModelNonNullableFieldException("Machine", "machineName");
        }

        for (Subassembly subassembly : machine.getMachineSubassemblies()) {
            machineSubassemblies.add(subassemblyRepository.findById(subassembly.getSubassemblyId())
                    .orElseThrow(() -> new ModelValueNotAllowed("Subassembly", "subassemblyId")));
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

        //CANNOT SET TO 0
        if (machine.getMachineQuantity() != 0) {
            updatedMachine.setMachineId(machine.getMachineId());
        }

        for (Subassembly subassembly : machine.getMachineSubassemblies()) {
            if (subassembly.getSubassemblyId() == null) {
                throw new ModelNonNullableFieldException("machineSubassemblies", "subassemblyId");
            } else if (subassembly.getSubassemblyId() < 1L) {
                throw new ModelValueNotAllowed("machineSubassemblies", "subassemblyId");
            }

            updatedMachine.getMachineSubassemblies().add(subassemblyRepository.findById(subassembly.getSubassemblyId())
                    .orElseThrow(() -> new ModelIdNotFoundException("Subassembly", subassembly.getSubassemblyId())));
        }

        return machineRepository.save(updatedMachine);
    }
}
