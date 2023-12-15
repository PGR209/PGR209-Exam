package com.PGR209.Exam.AddressTests;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.repository.AddressRepository;
import com.PGR209.Exam.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.List;

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
        Address newAddress = new Address("SingleTestAddressFirst", null);

        when(addressRepository.findById(index)).thenReturn(Optional.of(newAddress));

        assertThat(addressService.getAddressById(index)).isEqualTo(newAddress);
        assertThat(addressService.getAddressById(index).getStreet()).isEqualTo(newAddress.getStreet());
    }

    @Test
    public void shouldAddOneAddress() {
        Address addressCorrect = new Address("SingleTestAddressCorrect", null);
        Address addressWrong = new Address("SingleTestAddressWrong", null);

        when(addressRepository.save(addressCorrect)).thenReturn(addressCorrect);

        assertThat(addressService.newAddress(addressCorrect))
                .isEqualTo(addressCorrect)
                .isNotEqualTo(addressWrong);

    }

    @Test
    public void shouldGetAndDeleteOneAddress() {
        Long index = 0L;
        Address newAddress = new Address("DeleteMe", null);
        List<Address> addresses = List.of(newAddress);

        //when(addressRepository.deleteById(index)).then(addresses.remove(newAddress));
        //doNothing().when(addressRepository.deleteById(index));
    }

    @Test
    public void shouldUpdateOneAddress() {
        String newAddressName = "NewAddress";
        Address oldAddress = new Address("OldAddress", null);
        // NOT DONE
    }
}
