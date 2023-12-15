package com.PGR209.Exam.SalesOrder;

import com.PGR209.Exam.model.SalesOrder;
import com.PGR209.Exam.repository.SalesOrderRepository;
import com.PGR209.Exam.service.SalesOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
        SalesOrder newSalesOrder = new SalesOrder("SingleTestSalesOrderFirst");

        when(salesOrderRepository.findById(index)).thenReturn(Optional.of(newSalesOrder));

        assertThat(salesOrderService.getSalesOrderById(index)).isEqualTo(newSalesOrder);
        assertThat(salesOrderService.getSalesOrderById(index).getSalesOrderName()).isEqualTo(newSalesOrder.getSalesOrderName());
    }

    @Test
    public void shouldAddOneSalesOrder() {
        SalesOrder salesOrderCorrect = new SalesOrder("SingleTestSalesOrderCorrect");
        SalesOrder salesOrderWrong = new SalesOrder("SingleTestSalesOrderWrong");

        when(salesOrderRepository.save(salesOrderCorrect)).thenReturn(salesOrderCorrect);

        assertThat(salesOrderService.newSalesOrder(salesOrderCorrect))
                .isEqualTo(salesOrderCorrect)
                .isNotEqualTo(salesOrderWrong);

    }
}
