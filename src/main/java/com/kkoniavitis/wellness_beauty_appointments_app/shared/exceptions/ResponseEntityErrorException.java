package com.kkoniavitis.wellness_beauty_appointments_app.shared.exceptions;

import com.kkoniavitis.wellness_beauty_appointments_app.shared.models.ApiError;
import org.springframework.http.ResponseEntity;

public class ResponseEntityErrorException extends RuntimeException {
	private static final long serialVersionUID = -3156815846745801694L;

	private transient ResponseEntity<ApiError> apiResponse;

	public ResponseEntityErrorException(ResponseEntity<ApiError> apiResponse) {
		this.apiResponse = apiResponse;
	}

	public ResponseEntity<ApiError> getApiResponse() {
		return apiResponse;
	}
}
