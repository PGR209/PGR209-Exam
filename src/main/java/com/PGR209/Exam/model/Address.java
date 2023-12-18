package com.PGR209.Exam.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long addressId = 0L;

    //NOT NULL
    @Column(name = "address_name")
    private String addressName;

    @JsonManagedReference
    @ManyToMany (cascade = CascadeType.ALL)
    private List<Customer> addressCustomers = new ArrayList<>();

    public Address(String addressName, List<Customer> addressCustomers) {
        this.addressName = addressName;
        this.addressCustomers = addressCustomers;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s", addressId, addressName, addressCustomers);
    }
}
