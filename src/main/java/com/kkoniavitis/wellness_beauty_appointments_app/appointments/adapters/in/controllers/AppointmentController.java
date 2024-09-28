package com.kkoniavitis.wellness_beauty_appointments_app.appointments.adapters.in.controllers;

import com.kkoniavitis.wellness_beauty_appointments_app.appointments.adapters.out.presenters.view.resources.CreateAppointmentSummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.dtos.AppointmentRequestDto;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.ports.in.ICreateAppointmentUsecase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final ICreateAppointmentUsecase createAppointmentUsecase;
    public AppointmentController(ICreateAppointmentUsecase createAppointmentUsecase) {
        this.createAppointmentUsecase = createAppointmentUsecase;
    }

    @GetMapping("/all")
    public List<CreateAppointmentSummaryResponseResource> getAllAppointments() {
        return createAppointmentUsecase.findAll();  // Fetch all entries from DB
    }

    @PostMapping
    public CreateAppointmentSummaryResponseResource bookAppointment(@RequestBody AppointmentRequestDto appointment) {
        return createAppointmentUsecase.createAppointment(appointment);
    }

}
