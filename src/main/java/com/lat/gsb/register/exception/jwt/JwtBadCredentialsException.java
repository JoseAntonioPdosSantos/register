package com.lat.gsb.register.exception.jwt;

import org.springframework.security.authentication.BadCredentialsException;

public class JwtBadCredentialsException extends BadCredentialsException {

    public JwtBadCredentialsException() {
        super("Invalid credentials");
    }

}
