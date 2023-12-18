package com.PGR209.Exam.CustomerTests;


import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.model.SalesOrder;
import java.util.ArrayList;

import com.PGR209.Exam.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class CustomerRepositoryUnitTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    CustomerRepository repository;

    @Test
    public void shouldNotFindCustomerIfDbTableEmpty(){
        Iterable<Customer> customer = repository.findAll();
        assertThat(customer).isEmpty();
    }
    @Test
    public void shouldAddACustomer(){
        Customer customer = repository.save(new Customer("CustomerRepoUnitTest", "email@test", new ArrayList<Address>(), new ArrayList<SalesOrder>()));
        assertThat(customer.getCustomerName()).isEqualTo("CustomerRepoUnitTest");
    }

    @Test
    public void shouldUpdateExistingCustomer(){
        Customer customer = repository.save(new Customer("CustomerRepoUnitTest", "email@test", new ArrayList<Address>(), new ArrayList<SalesOrder>()));
        customer.setCustomerName("NEWCustomerName");
        repository.save(customer);
        Long id = customer.getCustomerId();
        Customer updatedCustomer = repository.findById(id).orElseThrow();
        assertThat(updatedCustomer.getCustomerName()).isEqualTo("NEWCustomerName");
    }

    @Test
    public void shouldReadCustomer(){
        Customer customer = repository.save(new Customer("CustomerRepoUnitTest", "email@test", new ArrayList<Address>(), new ArrayList<SalesOrder>()));
        Customer readCustomer = repository.findById(customer.getCustomerId()).orElseThrow();
        assertThat(readCustomer).isNotNull();
    }

    @Test
    public void shouldDeleteCustomer(){
        Customer customer = repository.save(new Customer("CustomerRepoUnitTest", "email@test", new ArrayList<Address>(), new ArrayList<SalesOrder>()));
        Long id = customer.getCustomerId();
        repository.deleteById(id);
        assertThat(repository.findById(id)).isEmpty();
    }
}
