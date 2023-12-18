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

    //NOT NULL
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salesorder_customer")
    private Customer salesOrderCustomer;

    @OneToMany(cascade = CascadeType.ALL)
    List<Machine> salesOrderMachines = new ArrayList<>();

    //NOT NULL
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salesorder_address")
    private Address salesOrderAddress;

    public SalesOrder(Customer salesOrderCustomer, List<Machine> machines, Address salesOrderAddress) {
        this.salesOrderCustomer = salesOrderCustomer;
        this.salesOrderMachines = machines;
        this.salesOrderAddress = salesOrderAddress;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - %s", salesOrderId, salesOrderCustomer, salesOrderAddress, salesOrderMachines);
    }
}