package com.PGR209.Exam.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Machine{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_seq_gen")
    @SequenceGenerator(name = "machine_seq_gen", sequenceName = "machine_seq", allocationSize = 1)
    @Column(name = "machine_id")
    private Long id = 0L;

    @Column(name = "machine_name")
    private String name;

    @Column(name = "machine_quantity")
    private int quantity;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Subassembly> subassembly = new ArrayList<>();
/*
    @ManyToMany(mappedBy = "machine")
    Set<SalesOrder> order;
*/
    public Machine(String name, int quantity, List<Subassembly> subassemblies, Set<SalesOrder> order) {
        this.name = name;
        this.quantity = quantity;
    }
}