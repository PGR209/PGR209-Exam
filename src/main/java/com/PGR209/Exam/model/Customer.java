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

   /* @ManyToMany
    @JoinTable(
            name = "customer_orders",
            joinColumns = @JoinColumn(name="customer_id"),
            inverseJoinColumns = @JoinColumn(name="order_id"))
    Set <SalesOrder> order;
*/

    public Customer(String name, String email, Set<Address> address, Set<SalesOrder> order) {
        this.name = name;
        this.email = email;
        this.address = address;
        //this.order = order;
    }
}