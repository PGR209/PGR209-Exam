package com.PGR209.Exam.SalesOrder;

import com.PGR209.Exam.model.Address;
import com.PGR209.Exam.model.Customer;
import com.PGR209.Exam.model.Machine;
import com.PGR209.Exam.model.SalesOrder;
import java.util.ArrayList;

import com.PGR209.Exam.repository.SalesOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SalesOrderRepositoryUnitTest {
    @Autowired
    SalesOrderRepository repository;

    @Test
    public void shouldNotFindSalesOrderIfDbTableEmpty(){
        Iterable<SalesOrder> salesOrder = repository.findAll();
        assertThat(salesOrder).isEmpty();
    }

    @Test
    public void shouldAddASalesOrder(){
        SalesOrder salesOrder = repository.save(
                new SalesOrder(
                        new Customer("CustomerRepoUnitTest",
                                "email@test",
                                new ArrayList<>(),
                                new ArrayList<>()) ,
                        new ArrayList<>(),
                        new Address()));
        assertThat(salesOrder.getSalesOrderCustomer().getCustomerName()).isEqualTo("CustomerRepoUnitTest");
    }

    @Test
    public void shouldUpdateExistingSalesOrder(){
        SalesOrder salesOrder = repository.save(new SalesOrder(new Customer(
                "customer",
                "email@test",
                new ArrayList<>(),
                new ArrayList<>()) ,
                new ArrayList<Machine>(),
                new Address()));
        Customer newCustomer = new Customer (
                "newcustomer",
                "email@test",
                new ArrayList<>(),
                new ArrayList<>());
        salesOrder.setSalesOrderCustomer(newCustomer);
        repository.save(salesOrder);
        Long id = salesOrder.getSalesOrderId();
        SalesOrder updatedSalesOrder = repository.findById(id).orElseThrow();
        assertThat(updatedSalesOrder.getSalesOrderCustomer().equals(newCustomer));
    }

    @Test
    public void shouldReadSalesOrder(){
        SalesOrder salesOrder = repository.save(new SalesOrder(new Customer(
                "customer",
                "email@test",
                new ArrayList<>(),
                new ArrayList<>()) ,
                new ArrayList<Machine>(),
                new Address()));
        SalesOrder readSalesOrder = repository.findById(salesOrder.getSalesOrderId()).orElseThrow();
        assertThat(readSalesOrder).isNotNull();
    }

    @Test
    public void shouldDeleteSalesOrder(){
        SalesOrder salesOrder = repository.save(new SalesOrder(new Customer(
                "customer", "email@test",
                new ArrayList<>(),
                new ArrayList<>()) ,
                new ArrayList<Machine>(),
                new Address()));
        Long id = salesOrder.getSalesOrderId();
        repository.deleteById(id);
        assertThat(repository.findById(id)).isEmpty();
    }
}
