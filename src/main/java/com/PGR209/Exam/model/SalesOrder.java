package com.PGR209.Exam.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    @Column(name = "salesorder_name")
    private String salesOrderName;

    public SalesOrder(String salesOrderName) {
        this.salesOrderName = salesOrderName;
    }

    /*
    @ManyToMany(mappedBy = "order")
    Set<Customer> customer;
    @ManyToMany
    @JoinTable(
            name = "order_machines",
            joinColumns = @JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name="machine_id"))
    Set <Machine> machine;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public SalesOrder(Set<Customer> customer, Set<Machine> machine, Address address) {
        this.customer = customer;
        this.machine = machine;
        this.address = address;
    }

     */
}