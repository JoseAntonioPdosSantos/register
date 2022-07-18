package com.lat.gsb.register.builder;

import com.lat.gsb.register.model.User;

public class UserBuilder {

    private UserBuilder() {
        throw new IllegalArgumentException();
    }

    public static User builder(Long id) {
        return User.builder()
            .id(id)
            .name("Some name")
            .password("some password")
            .username("Some username")
            .email("some_email@valid.com")
            .cellphone("+5599999999999")
            .build();
    }

}
