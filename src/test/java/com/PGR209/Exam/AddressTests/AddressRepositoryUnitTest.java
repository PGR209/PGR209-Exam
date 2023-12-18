package com.PGR209.Exam.AddressTests;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.repository.AddressRepository;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class AddressRepositoryUnitTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    AddressRepository repository;

    @Test
    public void shouldNotFindAddressIfDbTableEmpty(){
        Iterable<Address> address = repository.findAll();
        assertThat(address).isEmpty();
    }
    @Test
    public void shouldAddAnAddress(){
        Address address = repository.save(new Address("AddressRepoUnitTest", new ArrayList<Customer>()));
        assertThat(address.getAddressName()).isEqualTo("AddressRepoUnitTest");
    }

    @Test
    public void shouldUpdateExistingAddress(){
        Address address = repository.save(new Address("AddressRepoUnitTest", new ArrayList<Customer>()));
        address.setAddressName("NEWAddressName");
        repository.save(address);
        Long id = address.getAddressId();
        Address updatedAddress = repository.findById(id).orElseThrow();
        assertThat(updatedAddress.getAddressName()).isEqualTo("NEWAddressName");
    }

    @Test
    public void shouldReadAddress(){
        Address address = repository.save(new Address("AddressRepoUnitTest", new ArrayList<Customer>()));
        Address readAddress = repository.findById(address.getAddressId()).orElseThrow();
        assertThat(readAddress).isNotNull();
    }

    @Test
    public void shouldDeleteAddress(){
        Address address = repository.save(new Address("AddressRepoUnitTest", new ArrayList<Customer>()));
        Long id = address.getAddressId();
        repository.deleteById(id);
        assertThat(repository.findById(id)).isEmpty();
    }
}
