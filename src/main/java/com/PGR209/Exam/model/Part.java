package com.PGR209.Exam.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "part_seq_gen")
    @SequenceGenerator(name = "part_seq_gen", sequenceName = "part_seq", allocationSize = 1)
    @Column(name = "part_id")
    private Long partId = 0L;

    @Column(name = "part_name", nullable = false)
    private String partName;

    public Part (String partName){
        this.partName = partName;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", partId, partName);
    }
}
