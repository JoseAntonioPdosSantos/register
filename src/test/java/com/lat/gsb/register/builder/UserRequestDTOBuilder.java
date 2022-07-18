package com.lat.gsb.register.builder;

import com.lat.gsb.register.dto.user.UserRequestDTO;

public class UserRequestDTOBuilder {

    private UserRequestDTOBuilder() {
        throw new IllegalArgumentException();
    }

    public static UserRequestDTO builder(Long id) {
        return UserRequestDTO.builder()
            .id(id)
            .name("Some name")
            .username("some username")
            .password("some password")
            .email("some_email@valid.com")
            .cellphone("+5599999999999")
            .build();
    }

    public static UserRequestDTO builder() {
        return UserRequestDTO.builder()
            .id(1L)
            .name("Some name")
            .username("some username")
            .password("some password")
            .email("some_email@valid.com")
            .cellphone("+5599999999999")
            .build();
    }
}
