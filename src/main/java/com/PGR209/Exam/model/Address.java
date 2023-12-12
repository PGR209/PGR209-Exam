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
    @Column(name = "id")
    private Long id = 0L;

    @Column(name = "streetName")
    private String streetName;

    @Column(name = "number")
    private int number;

    @Column(name = "apartment")
    private String apartment;

    @Column(name = "zipcode")
    private int zipcode;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;
    @ManyToMany(mappedBy = "address")
    Set<Customer> customer;

    public Address(Long id, String streetName, int number, String apartment, int zipcode, String city, String country, Set<Customer> customer) {
        this.id = id;
        this.streetName = streetName;
        this.number = number;
        this.apartment = apartment;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
        this.customer = customer;
    }
}
