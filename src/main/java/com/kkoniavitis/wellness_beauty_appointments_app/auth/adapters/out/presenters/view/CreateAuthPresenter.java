package com.kkoniavitis.wellness_beauty_appointments_app.auth.adapters.out.presenters.view;


import com.kkoniavitis.wellness_beauty_appointments_app.auth.adapters.out.presenters.view.resources.CreateJwtAuthenticationResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.auth.usecases.ports.out.IAuthViewPresenter;
import org.springframework.stereotype.Service;

@Service
public class CreateAuthPresenter implements IAuthViewPresenter {
    @Override
    public CreateJwtAuthenticationResponseResource prepareSuccessView(String jwt) {
        return new CreateJwtAuthenticationResponseResource(jwt);
    }
}
