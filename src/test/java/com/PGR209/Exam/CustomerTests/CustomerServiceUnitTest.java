package com.PGR209.Exam.CustomerTests;

import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.repository.CustomerRepository;
import com.PGR209.Exam.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceUnitTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    public void shouldGetOneCustomer() {
        Long index = 1L;
        Customer newCustomer = new Customer("SingleTestCustomerFirst", "", null, null);

        when(customerRepository.findById(index)).thenReturn(Optional.of(newCustomer));

        assertThat(customerService.getCustomerById(index)).isEqualTo(newCustomer);
        assertThat(customerService.getCustomerById(index).getCustomerName()).isEqualTo(newCustomer.getCustomerName());
    }

    @Test
    public void shouldAddOneCustomer() {
        Customer customerCorrect = new Customer("SingleTestCustomerCorrect", "@test", new ArrayList<>(), new ArrayList<>());
        Customer customerWrong = new Customer("SingleTestCustomerWrong", "@test", new ArrayList<>(), new ArrayList<>());

        when(customerRepository.save(any())).thenReturn(customerCorrect);

        assertThat(customerService.newCustomer(customerCorrect))
                .isEqualTo(customerCorrect)
                .isNotEqualTo(customerWrong);

    }
}
