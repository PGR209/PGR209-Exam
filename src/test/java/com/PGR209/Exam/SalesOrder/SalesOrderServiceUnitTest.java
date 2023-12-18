package com.PGR209.Exam.SalesOrder;

import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.model.SalesOrder;
import com.PGR209.Exam.repository.SalesOrderRepository;
import com.PGR209.Exam.service.SalesOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SalesOrderServiceUnitTest {
    @MockBean
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private SalesOrderService salesOrderService;

    @Test
    public void shouldGetOneSalesOrder() {
        Long index = 1L;
        Customer customer = new Customer("SalesOrderUnitTest: " + LocalDateTime.now(), "", null, null);
        SalesOrder newSalesOrder = new SalesOrder(customer, null, null);

        when(salesOrderRepository.findById(index)).thenReturn(Optional.of(newSalesOrder));

        assertThat(salesOrderService.getSalesOrderById(index)).isEqualTo(newSalesOrder);
        assertThat(salesOrderService.getSalesOrderById(index).getSalesOrderCustomer().getCustomerName()).isEqualTo(customer.getCustomerName());
    }

    @Test
    public void shouldAddOneSalesOrder() {
        Customer correctCustomer = new Customer("SalesOrderUnitTest: " + LocalDateTime.now(), "", null, null);
        Customer wrongCustomer = new Customer("SalesOrderUnitTestWrong", "", null, null);
        SalesOrder salesOrderCorrect = new SalesOrder(correctCustomer, null, null);
        SalesOrder salesOrderWrong = new SalesOrder(wrongCustomer, null, null);

        when(salesOrderRepository.save(salesOrderCorrect)).thenReturn(salesOrderCorrect);

        assertThat(salesOrderService.newSalesOrder(salesOrderCorrect))
                .isEqualTo(salesOrderCorrect)
                .isNotEqualTo(salesOrderWrong);

    }
}
