package com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.out.gateways.ds.repositories;

import com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.out.gateways.ds.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICountryRepository extends JpaRepository<CountryEntity, Long> {

    List<CountryEntity> findByNameContainingIgnoreCase(String name);  // For search functionality
}
