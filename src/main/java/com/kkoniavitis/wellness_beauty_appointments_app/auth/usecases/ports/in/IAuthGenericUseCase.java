package com.kkoniavitis.wellness_beauty_appointments_app.auth.usecases.ports.in;


import com.kkoniavitis.wellness_beauty_appointments_app.auth.adapters.out.presenters.view.resources.CreateJwtAuthenticationResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.auth.usecases.models.CreateLoginRequestModel;
import com.kkoniavitis.wellness_beauty_appointments_app.auth.usecases.models.CreateSignUpRequestModel;
import org.springframework.stereotype.Service;

public interface IAuthGenericUseCase {
    CreateJwtAuthenticationResponseResource registerUser(CreateSignUpRequestModel signUpRequest);
    CreateJwtAuthenticationResponseResource authenticateUser(CreateLoginRequestModel loginRequest);
}
