package com.kkoniavitis.wellness_beauty_appointments_app.therapy.adapters.out.gateways.ds.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "therapies")
@Getter
@Setter
public class TherapyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
