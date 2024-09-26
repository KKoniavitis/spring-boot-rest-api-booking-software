package com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.gateways.ds.repositories;

import com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.gateways.ds.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICompanyRepository extends JpaRepository<CompanyEntity, Long> {
    CompanyEntity findCompanyEntitiesById(Long id);
    List<CompanyEntity> findByNameContainingIgnoreCase(String name);
}
