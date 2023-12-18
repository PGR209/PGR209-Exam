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
public class Machine{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_seq_gen")
    @SequenceGenerator(name = "machine_seq_gen", sequenceName = "machine_seq", allocationSize = 1)
    @Column(name = "machine_id")
    private Long machineId = 0L;

    //NOT NULL
    @Column(name = "machine_name")
    private String machineName;

    @Column(name = "machine_quantity")
    private int machineQuantity;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Subassembly> subassemblies = new ArrayList<>();

    public Machine(String machineName, int machineQuantity, List<Subassembly> subassemblies) {
        this.machineName = machineName;
        this.machineQuantity = machineQuantity;
        this.subassemblies = subassemblies;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - %s", machineId, machineName, machineQuantity, subassemblies);
    }
}