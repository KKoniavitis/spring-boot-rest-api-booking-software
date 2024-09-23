package com.kkoniavitis.wellness_beauty_appointments_app.therapy.adapters.in.controllers;

import com.kkoniavitis.wellness_beauty_appointments_app.therapy.adapters.out.presenters.view.resources.CreateTherapySummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.ports.in.ICreateTherapyUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/therapies")
public class TherapyController {

    private final ICreateTherapyUseCase createTherapyUseCase;
    public TherapyController(ICreateTherapyUseCase createTherapyUseCase) {
        this.createTherapyUseCase = createTherapyUseCase;
    }

    @GetMapping("/all")
    public List<CreateTherapySummaryResponseResource> getAllTherapies() {
        return createTherapyUseCase.findAll();
    }

    @GetMapping("/search")
    public List<CreateTherapySummaryResponseResource> searchTherapies(@RequestParam("q") String query) {
        return createTherapyUseCase.searchTherapies(query);
    }
}
