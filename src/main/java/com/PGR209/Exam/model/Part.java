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
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "part_seq_gen")
    @SequenceGenerator(name = "part_seq_gen", sequenceName = "part_seq", allocationSize = 1)
    @Column(name = "part_id")
    private Long id = 0L;

    @Column(name = "part_name")
    private String partName;

    public Part(String partName) {
        this.partName = partName;
    }

    /*
    @Column(name = "part_name")
    private String part;

    @ManyToMany(mappedBy = "parts")
    Set<Subassembly> subassemblies;

    private Part (String part, Set<Subassembly> subassemblies){
        this.part = part;
        this.subassemblies = subassemblies;
    }

     */
}
