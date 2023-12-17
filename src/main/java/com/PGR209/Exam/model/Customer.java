package com.PGR209.Exam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long id = 0L;

    @Column(name = "customer_name", nullable = false)
    private String name;

    @Column(name = "customer_email", nullable = false, unique = true)
    private String email;
    @JsonBackReference
    @ManyToMany (cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany (cascade = CascadeType.ALL)
    private List<SalesOrder> salesOrders = new ArrayList<>();

    public Customer(String name, String email, List<Address> addresses, List<SalesOrder> salesOrders) {
        this.name = name;
        this.email = email;
        this.addresses = addresses;
        this.salesOrders = salesOrders;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - %s -- %s", id, name, email, addresses, salesOrders);
    }
}