package com.kkoniavitis.wellness_beauty_appointments_app.shared.exceptions;

import com.kkoniavitis.wellness_beauty_appointments_app.shared.models.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private ApiError apiError;

	public BadRequestException(ApiError apiError) {
		super();
		this.apiError = apiError;
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiError getApiResponse() {
		return apiError;
	}
}
