package com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateInfoRequestModel {

	@NotBlank
	private String street;

	@NotBlank
	private String suite;

	@NotBlank
	private String city;

	@NotBlank
	private String zipcode;

	private String companyName;

	private String catchPhrase;

	private String bs;

	private String website;

	private String phone;

	private String lat;

	private String lng;
}
