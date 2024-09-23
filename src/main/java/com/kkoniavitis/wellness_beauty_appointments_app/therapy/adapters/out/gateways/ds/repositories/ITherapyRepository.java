package com.kkoniavitis.wellness_beauty_appointments_app.therapy.adapters.out.gateways.ds.repositories;

import com.kkoniavitis.wellness_beauty_appointments_app.therapy.adapters.out.gateways.ds.entities.TherapyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITherapyRepository extends JpaRepository<TherapyEntity, Long> {

    List<TherapyEntity> findByNameContainingIgnoreCase(String name);
}
