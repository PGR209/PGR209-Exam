package com.PGR209.Exam.MachineTests;

import com.PGR209.Exam.model.*;

import java.util.ArrayList;

import com.PGR209.Exam.repository.MachineRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MachineRepositoryUnitTest {
    @Autowired
    MachineRepository repository;

    @Test
    public void shouldNotFindMachineIfDbTableEmpty(){
        Iterable<Machine> machine = repository.findAll();
        assertThat(machine).isEmpty();
    }

    @Test
    public void shouldAddAMachine(){
        Machine machine = repository.save(new Machine("MachineRepoUnitTest",
                2,
                new ArrayList<Subassembly>()));
        assertThat(machine.getMachineName()).isEqualTo("MachineRepoUnitTest");
    }

    @Test
    public void shouldUpdateExistingMachine(){
        Machine machine = repository.save(new Machine("MachineRepoUnitTest",
                2,
                new ArrayList<Subassembly>()));
        machine.setMachineName("NEWMachineName");
        repository.save(machine);
        Long id = machine.getMachineId();
        Machine updatedMachine = repository.findById(id).orElseThrow();
        assertThat(updatedMachine.getMachineName()).isEqualTo("NEWMachineName");
    }

    @Test
    public void shouldReadMachine(){
        Machine machine = repository.save(new Machine("MachineRepoUnitTest",
                2,
                new ArrayList<Subassembly>()));
        Machine readMachine = repository.findById(machine.getMachineId()).orElseThrow();
        assertThat(readMachine).isNotNull();
    }

    @Test
    public void shouldDeleteMachine(){
        Machine machine = repository.save(new Machine(
                "MachineRepoUnitTest",
                2,
                new ArrayList<Subassembly>()));
        Long id = machine.getMachineId();
        repository.deleteById(id);
        assertThat(repository.findById(id)).isEmpty();
    }
}
