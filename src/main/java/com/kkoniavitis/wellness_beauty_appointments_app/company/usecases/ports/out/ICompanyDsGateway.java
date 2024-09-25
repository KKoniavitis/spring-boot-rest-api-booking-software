package com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.ports.out;

import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.dtos.CreateCompanyDsDto;

import java.util.List;

public interface ICompanyDsGateway {
    List<CreateCompanyDsDto> findAll();
    List<CreateCompanyDsDto> searchCompanies(String query);
}
