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
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salesorder_seq_gen")
    @SequenceGenerator(name = "salesorder_seq_gen", sequenceName = "salesorder_seq", allocationSize = 1)
    @Column(name = "salesorder_id")
    private Long salesOrderId = 0L;

    @ManyToOne
    @JoinColumn(name = "salesorder_customer", nullable = false)
    private Customer salesOrderCustomer;

    @OneToMany
    List<Machine> machines = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "salesorder_address", nullable = false)
    private Address salesOrderAddress;

    public SalesOrder(Customer salesOrderCustomer, List<Machine> machines, Address salesOrderAddress) {
        this.salesOrderCustomer = salesOrderCustomer;
        this.machines = machines;
        this.salesOrderAddress = salesOrderAddress;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - %s", salesOrderId, salesOrderCustomer, salesOrderAddress, machines);
    }
}