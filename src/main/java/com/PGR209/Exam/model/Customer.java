package com.PGR209.Exam.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter @Setter @ToString
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_gen")
    @SequenceGenerator(name = "customer_seq_gen", sequenceName = "customer_seq", allocationSize = 1)
    @Column(name = "customer_id")
    private Long id = 0L;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "customer_email")
    private String email;

    @ManyToMany //creates a many to many table called customer_address w their IDs
    @JoinTable(
            name = "customer_address",
            joinColumns = @JoinColumn(name="customer_id"),
            inverseJoinColumns = @JoinColumn(name ="address_id"))
    Set<Address> address;

    @OneToMany (cascade = CascadeType.ALL)
    private List<SalesOrder> salesOrder = new ArrayList<>();


    public Customer(String name, String email, Set<Address> address, List<SalesOrder> salesOrder) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.salesOrder = salesOrder;
    }
}