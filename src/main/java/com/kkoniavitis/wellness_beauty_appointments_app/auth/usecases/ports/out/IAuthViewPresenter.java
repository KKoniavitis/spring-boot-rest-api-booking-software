package com.kkoniavitis.wellness_beauty_appointments_app.auth.usecases.ports.out;


import com.kkoniavitis.wellness_beauty_appointments_app.auth.adapters.out.presenters.view.resources.CreateJwtAuthenticationResponseResource;

public interface IAuthViewPresenter {
    CreateJwtAuthenticationResponseResource prepareSuccessView(String jwt);
}
