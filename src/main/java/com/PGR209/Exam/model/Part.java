package com.PGR209.Exam.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "part_seq_gen")
    @SequenceGenerator(name = "part_seq_gen", sequenceName = "part_seq", allocationSize = 1)
    @Column(name = "part_id")
    private Long id = 0L;

    @Column(name = "part_name")
    private String part;

    @ManyToMany(mappedBy = "subassembly")
    Set<Subassembly> subassemblies;

    private Part (Long id, String part, Set<Subassembly> subassemblies){
        this.id = id;
        this.part = part;
        this.subassemblies = subassemblies;
    }
}
