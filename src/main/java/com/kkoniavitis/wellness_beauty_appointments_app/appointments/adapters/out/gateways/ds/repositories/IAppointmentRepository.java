package com.kkoniavitis.wellness_beauty_appointments_app.appointments.adapters.out.gateways.ds.repositories;

import com.kkoniavitis.wellness_beauty_appointments_app.appointments.adapters.out.gateways.ds.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
}
