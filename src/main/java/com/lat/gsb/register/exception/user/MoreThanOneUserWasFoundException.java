package com.lat.gsb.register.exception.user;

public class MoreThanOneUserWasFoundException extends RuntimeException {
    public MoreThanOneUserWasFoundException() {
        super("More than one user was found");
    }
}
