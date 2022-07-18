package com.lat.gsb.register.builder;

import com.lat.gsb.register.dto.user.UserDTO;

public class UserDTOBuilder {

    private UserDTOBuilder() {
        throw new IllegalArgumentException();
    }

    public static UserDTO builder(Long id) {
        return UserDTO.builder()
            .id(id)
            .name("Some name")
            .password("some password")
            .username("some username")
            .email("some_email@valid.com")
            .cellphone("+5599999999999")
            .build();
    }

    public static UserDTO builder() {
        return UserDTO.builder()
            .id(1L)
            .name("Some name")
            .password("some password")
            .username("some username")
            .email("some_email@valid.com")
            .cellphone("+5599999999999")
            .build();
    }
}
