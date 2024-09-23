package com.kkoniavitis.wellness_beauty_appointments_app.auth.adapters.in.controllers;

import com.kkoniavitis.wellness_beauty_appointments_app.auth.adapters.out.presenters.view.resources.CreateJwtAuthenticationResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.auth.usecases.models.CreateLoginRequestModel;
import com.kkoniavitis.wellness_beauty_appointments_app.auth.usecases.models.CreateSignUpRequestModel;
import com.kkoniavitis.wellness_beauty_appointments_app.auth.usecases.ports.in.IAuthGenericUseCase;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final IAuthGenericUseCase authGenericUseCase;

	public AuthController(IAuthGenericUseCase authGenericUseCase) {
		this.authGenericUseCase = authGenericUseCase;
	}


	@PostMapping("/signin")
	public CreateJwtAuthenticationResponseResource authenticateUser(@Valid @RequestBody CreateLoginRequestModel loginRequest) {
		return authGenericUseCase.authenticateUser(loginRequest);
	}

	@PostMapping("/signup")
	public CreateJwtAuthenticationResponseResource registerUser(@Valid @RequestBody CreateSignUpRequestModel signUpRequest) {
		return authGenericUseCase.registerUser(signUpRequest);
	}
}
