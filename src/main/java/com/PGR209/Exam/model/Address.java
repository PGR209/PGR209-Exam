package com.PGR209.Exam.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter @Setter
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

    @ManyToMany (cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();

    public Address(String street, List<Customer> customers) {
        this.street = street;
        this.customers = customers;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s", id, street, customers);
    }
}
