package com.lat.gsb.register.exception.jwt;


public class InvalidSubjectTokenException extends RuntimeException {
    public InvalidSubjectTokenException() {
        super("Invalid subject token exception");

    }
}
