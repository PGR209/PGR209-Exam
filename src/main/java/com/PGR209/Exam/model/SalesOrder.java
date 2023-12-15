package com.PGR209.Exam.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private Long id = 0L;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany
    List<Machine> machines;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public SalesOrder(Customer customer, List<Machine> machines, Address address) {
        this.customer = customer;
        this.machines = machines;
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - %s", id, customer, address, machines);
    }
}