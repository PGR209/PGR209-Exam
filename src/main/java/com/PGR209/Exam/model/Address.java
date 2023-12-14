package com.PGR209.Exam.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter @Setter @ToString
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq_gen")
    @SequenceGenerator(name = "address_seq_gen", sequenceName = "address_seq", allocationSize = 1)
    @Column(name = "address_id")
    private Long id = 0L;

    @Column(name = "street")
    private String street;

    @ManyToMany(mappedBy = "address")
    Set<Customer> customer;

    public Address(String street, Set<Customer> customer) {
        this.street = street;
        this.customer = customer;
    }
}
