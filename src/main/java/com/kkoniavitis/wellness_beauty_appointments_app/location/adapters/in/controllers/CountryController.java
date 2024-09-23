package com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.in.controllers;

import com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.out.presenters.view.resources.CreateCountrySummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.ports.in.ICreateCountryUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class CountryController {

    private final ICreateCountryUseCase createCountryUseCase;
    public CountryController(ICreateCountryUseCase createCountryUseCase) {
        this.createCountryUseCase = createCountryUseCase;
    }

    @GetMapping("/all")
    public List<CreateCountrySummaryResponseResource> getAllCountries() {
        return createCountryUseCase.findAll();  // Fetch all entries from DB
    }

    @GetMapping("/search")
    public List<CreateCountrySummaryResponseResource> searchCountries(@RequestParam("q") String query) {
        return createCountryUseCase.searchCountries(query);
    }
}
