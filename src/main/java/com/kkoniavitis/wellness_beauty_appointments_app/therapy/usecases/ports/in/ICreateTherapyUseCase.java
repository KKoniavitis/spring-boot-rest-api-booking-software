package com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.ports.in;

import com.kkoniavitis.wellness_beauty_appointments_app.therapy.adapters.out.presenters.view.resources.CreateTherapySummaryResponseResource;

import java.util.List;

public interface ICreateTherapyUseCase {
    List<CreateTherapySummaryResponseResource> findAll();
    List<CreateTherapySummaryResponseResource> searchTherapies(String query);
}
