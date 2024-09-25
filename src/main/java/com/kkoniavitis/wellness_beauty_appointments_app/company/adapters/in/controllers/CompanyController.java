package com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.in.controllers;

import com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.presenters.view.resources.CreateCompanySummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.ports.in.ICreateCompanyUsecase;
import com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.out.presenters.view.resources.CreateCountrySummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.ports.in.ICreateCountryUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final ICreateCompanyUsecase createCompanyUseCase;
    public CompanyController(ICreateCompanyUsecase createCompanyUseCase) {
        this.createCompanyUseCase = createCompanyUseCase;
    }

    @GetMapping("/all")
    public List<CreateCompanySummaryResponseResource> getAllCompanies() {
        return createCompanyUseCase.findAll();  // Fetch all entries from DB
    }

    @GetMapping("/search")
    public List<CreateCompanySummaryResponseResource> searchCompanies(@RequestParam("q") String query) {
        return createCompanyUseCase.searchCompanies(query);
    }
}
