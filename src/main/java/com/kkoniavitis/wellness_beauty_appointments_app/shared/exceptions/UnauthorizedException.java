package com.kkoniavitis.wellness_beauty_appointments_app.shared.exceptions;

import com.kkoniavitis.wellness_beauty_appointments_app.shared.models.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private ApiError apiError;

	private String message;

	public UnauthorizedException(ApiError apiError) {
		super();
		this.apiError = apiError;
	}

	public UnauthorizedException(String message) {
		super(message);
		this.message = message;
	}

	public UnauthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiError getApiResponse() {
		return apiError;
	}

	public void setApiResponse(ApiError apiError) {
		this.apiError = apiError;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
