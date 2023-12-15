package com.PGR209.Exam.Part;

import com.PGR209.Exam.model.Part;
import com.PGR209.Exam.repository.PartRepository;
import com.PGR209.Exam.service.PartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PartServiceUnitTest {
    @MockBean
    private PartRepository partRepository;

    @Autowired
    private PartService partService;

    @Test
    public void shouldGetOnePart() {
        Long index = 1L;
        Part newPart = new Part("SingleTestPartFirst", null);

        when(partRepository.findById(index)).thenReturn(Optional.of(newPart));

        assertThat(partService.getPartById(index)).isEqualTo(newPart);
        assertThat(partService.getPartById(index).getPart()).isEqualTo(newPart.getPart());
    }

    @Test
    public void shouldAddOnePart() {
        Part partCorrect = new Part("SingleTestPartCorrect", null);
        Part partWrong = new Part("SingleTestPartWrong", null);

        when(partRepository.save(partCorrect)).thenReturn(partCorrect);

        assertThat(partService.newPart(partCorrect))
                .isEqualTo(partCorrect)
                .isNotEqualTo(partWrong);

    }
}