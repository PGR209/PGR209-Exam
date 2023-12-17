package com.PGR209.Exam.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_gen")
    @SequenceGenerator(name = "customer_seq_gen", sequenceName = "customer_seq", allocationSize = 1)
    @Column(name = "customer_id")
    private Long customerId = 0L;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_email", nullable = false, unique = true)
    private String customerEmail;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<Address> customerAddresses = new ArrayList<>();

    @OneToMany (cascade = CascadeType.ALL)
    private List<SalesOrder> customerSalesOrders = new ArrayList<>();

    public Customer(String customerName, String customerEmail, List<Address> customerAddresses, List<SalesOrder> customerSalesOrders) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddresses = customerAddresses;
        this.customerSalesOrders = customerSalesOrders;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - %s -- %s", customerId, customerName, customerEmail, customerAddresses, customerSalesOrders);
    }
}