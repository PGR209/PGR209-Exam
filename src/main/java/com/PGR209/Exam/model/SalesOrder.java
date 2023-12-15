package com.PGR209.Exam.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salesorder_seq_gen")
    @SequenceGenerator(name = "salesorder_seq_gen", sequenceName = "salesorder_seq", allocationSize = 1)
    @Column(name = "salesorder_id")
    private Long id = 0L;

    @Column(name = "sales_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

/*
    @ManyToMany
    @JoinTable(
            name = "order_machines",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name="machine_id"))
    Set <Machine> machine;
*/
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public SalesOrder(Customer customer, String name, /*Set<Machine> machine,*/ Address address) {
        this.customer = customer;
        this.name = name;
        //this.machine = machine;
        this.address = address;
    }
}