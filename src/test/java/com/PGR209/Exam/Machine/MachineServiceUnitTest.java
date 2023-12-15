package com.PGR209.Exam.Machine;

import com.PGR209.Exam.model.Machine;
import com.PGR209.Exam.repository.MachineRepository;
import com.PGR209.Exam.service.MachineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MachineServiceUnitTest {
    @MockBean
    private MachineRepository machineRepository;

    @Autowired
    private MachineService machineService;

    @Test
    public void shouldGetOneMachine() {
        Long index = 1L;
        Machine newMachine = new Machine("MachineUnitTest: " + LocalDateTime.now(), 0, null);

        when(machineRepository.findById(index)).thenReturn(Optional.of(newMachine));

        assertThat(machineService.getMachineById(index)).isEqualTo(newMachine);
        assertThat(machineService.getMachineById(index).getName()).isEqualTo(newMachine.getName());
    }

    @Test
    public void shouldAddOneMachine() {
        Machine machineCorrect = new Machine("MachineUnitTest: " + LocalDateTime.now(), 0, null);
        Machine machineWrong = new Machine("MachineUnitTestWrong", 0, null);

        when(machineRepository.save(machineCorrect)).thenReturn(machineCorrect);

        assertThat(machineService.newMachine(machineCorrect))
                .isEqualTo(machineCorrect)
                .isNotEqualTo(machineWrong);

    }
}
