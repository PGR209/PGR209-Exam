package com.PGR209.Exam.AddressTests;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.repository.AddressRepository;
import com.PGR209.Exam.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AddressServiceUnitTest {
    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @Test
    public void shouldGetOneAddress() {
        Long index = 1L;
        Address newAddress = new Address("AddressUnitTest: " + LocalDateTime.now(), null);

        when(addressRepository.findById(index)).thenReturn(Optional.of(newAddress));

        assertThat(addressService.getAddressById(index)).isEqualTo(newAddress);
        assertThat(addressService.getAddressById(index).getAddressName()).isEqualTo(newAddress.getAddressName());
    }

    @Test
    public void shouldAddOneAddress() {
        Address addressCorrect = new Address("AddressUnitTest: " + LocalDateTime.now(), new ArrayList<>());
        Address addressWrong = new Address("AddressUnitTestWrong", new ArrayList<>());

        when(addressRepository.save(any())).thenReturn(addressCorrect);

        assertThat(addressService.newAddress(addressCorrect))
                .isEqualTo(addressCorrect)
                .isNotEqualTo(addressWrong);

    }
}
