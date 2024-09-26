package com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.in.controllers;

import com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.presenters.view.resources.CreateCompanySummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.ports.in.ICreateCompanyUsecase;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public CreateCompanySummaryResponseResource getCompanyById(@PathVariable Long id){
        return createCompanyUseCase.getCompanyById(id);
    }

    @GetMapping("/search")
    public List<CreateCompanySummaryResponseResource> searchCompanies(@RequestParam("q") String query) {
        return createCompanyUseCase.searchCompanies(query);
    }
}
