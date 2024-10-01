package com.kkoniavitis.wellness_beauty_appointments_app.shared.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
