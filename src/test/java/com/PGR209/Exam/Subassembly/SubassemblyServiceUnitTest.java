package com.PGR209.Exam.Subassembly;

import com.PGR209.Exam.model.Subassembly;
import com.PGR209.Exam.repository.SubassemblyRepository;
import com.PGR209.Exam.service.SubassemblyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SubassemblyServiceUnitTest {
    @MockBean
    private SubassemblyRepository subassemblyRepository;

    @Autowired
    private SubassemblyService subassemblyService;

    @Test
    public void shouldGetOneSubassembly() {
        Long index = 1L;
        Subassembly newSubassembly = new Subassembly("SubassemblyUnitTest: " + LocalDateTime.now(), null);

        when(subassemblyRepository.findById(index)).thenReturn(Optional.of(newSubassembly));

        assertThat(subassemblyService.getSubassemblyById(index)).isEqualTo(newSubassembly);
        assertThat(subassemblyService.getSubassemblyById(index).getName()).isEqualTo(newSubassembly.getName());
    }

    @Test
    public void shouldAddOneSubassembly() {
        Subassembly subassemblyCorrect = new Subassembly("SubassemblyUnitTest: " + LocalDateTime.now(), null);
        Subassembly subassemblyWrong = new Subassembly("SubassemblyUnitTestWrong", null);

        when(subassemblyRepository.save(subassemblyCorrect)).thenReturn(subassemblyCorrect);

        assertThat(subassemblyService.newSubassembly(subassemblyCorrect))
                .isEqualTo(subassemblyCorrect)
                .isNotEqualTo(subassemblyWrong);

    }
}
