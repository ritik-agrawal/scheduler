package com.assignment.scheduler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Operators")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operator {

    public Operator(String name){
        this.name = name;
        this.appointments = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "operator", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

}
