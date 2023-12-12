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
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_gen")
    @SequenceGenerator(name = "order_seq_gen", sequenceName = "order_seq", allocationSize = 1)
    @Column(name = "order_id")
    private Long id = 0L;

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

    public Order(Set<Customer> customer, Set<Machine> machine, Address address) {
        this.customer = customer;
        this.machine = machine;
        this.address = address;
    }
}