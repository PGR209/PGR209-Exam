package com.PGR209.Exam.model;

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
public class Subassembly {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subassembly_seq_gen")
    @SequenceGenerator(name = "subassembly_seq_gen", sequenceName = "subassembly_seq", allocationSize = 1)
    @Column(name = "subassembly_id")
    private Long id = 0L;

    @Column(name = "subassembly_name", nullable = false)
    private String name;

    @OneToMany
    List<Part> parts = new ArrayList<>();

    public Subassembly (String name, List<Part> parts){
        this.name = name;
        this.parts = parts;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s", id, name, parts);
    }
}
