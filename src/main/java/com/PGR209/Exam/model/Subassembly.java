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
public class Subassembly {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subassembly_seq_gen")
    @SequenceGenerator(name = "subassembly_seq_gen", sequenceName = "subassembly_seq", allocationSize = 1)
    @Column(name = "subassembly_id")
    private Long id = 0L;

    @Column(name = "subassembly_name")
    private String subassemblyName;

    public Subassembly(String subassemblyName) {
        this.subassemblyName = subassemblyName;
    }

    /*
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;

    @ManyToMany
    @JoinTable(
            name = "subassembly_parts",
            joinColumns = @JoinColumn(name="parts_id"),
            inverseJoinColumns = @JoinColumn(name="subassembly_id"))
    Set <Part> parts;

    public Subassembly (String name, Machine machine, Set<Part> parts){
        this.name=name;
        this.machine=machine;
        this.parts = parts;
    }

     */
}
