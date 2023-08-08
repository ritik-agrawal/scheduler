package com.assignment.scheduler.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table (name = "appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    public Appointment(Operator operator, Integer from, Integer to, String customer){
        this.operator = operator;
        this.from = from;
        this.to = to;
        this.customerName = customer;
        this.cancelled = false;
        this.date = ZonedDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Operator operator;

    @Column(name = "slot_from", columnDefinition = "integer default 0")
    private Integer from;

    @Column(name = "slot_to", columnDefinition = "integer default 0")
    private Integer to;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "cancelled")
    private boolean cancelled;

    @Column(name = "date")
    private ZonedDateTime date;

}
