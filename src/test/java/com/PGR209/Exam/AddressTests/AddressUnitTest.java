package com.PGR209.Exam.AddressTests;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.repository.AddressRepository;
import com.PGR209.Exam.service.AddressService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AddressUnitTest {
    @MockBean
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @Before()
    public void addressSetup() {
        Address newAddress = new Address("TestAddress", null);

        addressRepository.save(newAddress);
    }

    @Test
    public void shouldGetOneAddress() {
        assertThat(addressRepository.findAll().size()).isEqualTo(1);
    }
}
