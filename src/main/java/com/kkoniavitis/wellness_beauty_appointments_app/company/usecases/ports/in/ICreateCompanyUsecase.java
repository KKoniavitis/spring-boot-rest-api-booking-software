package com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.ports.in;

import com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.presenters.view.resources.CreateCompanySummaryResponseResource;

import java.util.List;

public interface ICreateCompanyUsecase {
    CreateCompanySummaryResponseResource getCompanyById(Long id);
    List<CreateCompanySummaryResponseResource> findAll();
    List<CreateCompanySummaryResponseResource> searchCompanies(String query);
}
