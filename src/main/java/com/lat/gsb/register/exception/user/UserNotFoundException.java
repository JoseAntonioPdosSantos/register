package com.lat.gsb.register.exception.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found exception");
    }
}
