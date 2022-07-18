package com.lat.gsb.register.controller;

import com.lat.gsb.register.IntegrationTestConfig;
import com.lat.gsb.register.builder.UserBuilder;
import com.lat.gsb.register.builder.UserDTOBuilder;
import com.lat.gsb.register.dto.ErrorDTO;
import com.lat.gsb.register.dto.user.UserDTO;
import com.lat.gsb.register.mapper.UserMapper;
import com.lat.gsb.register.service.UserService;
import com.lat.gsb.register.util.CriptUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("UserControllerImp Tests")
class UserControllerImpTest extends IntegrationTestConfig {

    @Autowired
    private UserService service;
    @Autowired
    private UserMapper mapper;

    @SneakyThrows
    @Test
    @DisplayName("Create user when return user with successful")
    void save() {
        var userDTO = UserDTOBuilder.builder(1L);

        var response = mockMvc.perform(post("/user")
                .headers(defaultHeaders())
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO))
            )
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString();

        var responseDTO = objectMapper.readValue(response, UserDTO.class);
        assertNotNull(responseDTO);
        assertThat(responseDTO.getId()).isNotNull();
        assertThat(responseDTO.getName()).isNotNull().isEqualTo("Some name");
        assertThat(responseDTO.getEmail()).isNotNull().isEqualTo("some_email@valid.com");
        assertThat(responseDTO.getCellphone()).isNotNull().isEqualTo("+5599999999999");
        assertThat(responseDTO.getUsername()).isNotNull().isEqualTo("some username");
        assertThat(responseDTO.getPassword()).isNotNull().isEqualTo(CriptUtil.encript("some password"));
    }

    @Test
    @DisplayName("Create user when return bad request with fail")
    void save_when_fail() throws Exception {
        var userDTO = UserDTOBuilder.builder(1L);
        userDTO.setName("1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-"
            + "418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1"
            + "-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f"
            + "1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-"
            + "418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1"
            + "-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f"
        );

        var response = mockMvc.perform(post("/user")
                .headers(defaultHeaders())
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO))
            )
            .andExpect(status().isBadRequest())
            .andReturn()
            .getResponse()
            .getContentAsString();

        var errorDTO = objectMapper.readValue(response, ErrorDTO.class);
        assertNotNull(errorDTO);
        assertThat(errorDTO.getName()).isNotNull();
        assertThat(errorDTO.getMessage()).isNotNull().isEqualTo("User unexpected error");
        assertThat(errorDTO.getDetails()).isNotNull();
    }

    @SneakyThrows
    @Test
    @DisplayName("Update user when return user with successful")
    void update() {
        var user = UserBuilder.builder(1L);

        var userDTO = service.create(mapper.map(user));
        userDTO.setEmail("change_email@email.valid");
        userDTO.setPassword("some password");

        var response = mockMvc.perform(put("/user/%s".formatted(userDTO.getId()))
                .headers(defaultHeaders())
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO))
            )
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        var responseDTO = objectMapper.readValue(response, UserDTO.class);
        assertNotNull(responseDTO);
        assertThat(responseDTO.getId()).isNotNull().isEqualTo(userDTO.getId());
        assertThat(responseDTO.getName()).isNotNull().isEqualTo("Some name");
        assertThat(responseDTO.getEmail()).isNotNull().isEqualTo("change_email@email.valid");
        assertThat(responseDTO.getCellphone()).isNotNull().isEqualTo("+5599999999999");
        assertThat(responseDTO.getUsername()).isNotNull().isEqualTo("Some username");
        assertThat(responseDTO.getPassword()).isNotNull().isEqualTo(CriptUtil.encript("some password"));
    }

    @Test
    @DisplayName("Update user when return bad request with fail")
    void update_when_fail() throws Exception {
        var user = UserBuilder.builder(1L);

        var userDTO = service.create(mapper.map(user));
        userDTO.setEmail("change_email@email.valid");
        userDTO.setName("1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-"
            + "418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1"
            + "-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f"
            + "1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-"
            + "418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1"
            + "-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f"
        );

        var response = mockMvc.perform(put("/user/%s".formatted(userDTO.getId()))
                .headers(defaultHeaders())
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO))
            )
            .andExpect(status().isBadRequest())
            .andReturn()
            .getResponse()
            .getContentAsString();

        var errorDTO = objectMapper.readValue(response, ErrorDTO.class);
        assertNotNull(errorDTO);
        assertThat(errorDTO.getName()).isNotNull();
        assertThat(errorDTO.getMessage()).isNotNull().isEqualTo("User unexpected error");
        assertThat(errorDTO.getDetails()).isNotNull();
    }

    @SneakyThrows
    @Test
    @DisplayName("Find all user when return users with successful")
    void findAll() {
        var user1 = UserBuilder.builder(null);
        service.create(mapper.map(user1));
        var user2 = UserBuilder.builder(null);
        service.create(mapper.map(user2));
        var user3 = UserBuilder.builder(null);
        service.create(mapper.map(user3));

        var response = mockMvc.perform(get("/user")
                .headers(defaultHeaders())
                .contentType(APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        var userDTOs = objectMapper.readValue(response, java.util.List.class);

        assertThat(userDTOs).isNotEmpty().hasSizeGreaterThanOrEqualTo(3);
    }

    @SneakyThrows
    @Test
    @DisplayName("Find one user by id when return user with successful")
    void findOneById() {
        var user = UserBuilder.builder(1L);
        var userDTO = service.create(mapper.map(user));

        var response = mockMvc.perform(get("/user/%s".formatted(userDTO.getId()))
                .headers(defaultHeaders())
                .contentType(APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        var responseDTO = objectMapper.readValue(response, UserDTO.class);

        assertNotNull(responseDTO);
        assertThat(responseDTO.getId()).isNotNull().isEqualTo(userDTO.getId());
        assertThat(responseDTO.getName()).isNotNull().isEqualTo("Some name");
        assertThat(responseDTO.getEmail()).isNotNull().isEqualTo("some_email@valid.com");
        assertThat(responseDTO.getCellphone()).isNotNull().isEqualTo("+5599999999999");
        assertThat(responseDTO.getUsername()).isNotNull().isEqualTo("Some username");
        assertThat(responseDTO.getPassword()).isNotNull().isEqualTo(CriptUtil.encript("some password"));
    }

    @SneakyThrows
    @Test
    @DisplayName("Find one user by id when return bad request with fail")
    void findOneById_when_fail() {

        var response = mockMvc.perform(get("/user/%s".formatted(-1))
                .headers(defaultHeaders())
                .contentType(APPLICATION_JSON)
            )
            .andExpect(status().isBadRequest())
            .andReturn()
            .getResponse()
            .getContentAsString();

        var errorDTO = objectMapper.readValue(response, ErrorDTO.class);
        assertNotNull(errorDTO);
        assertThat(errorDTO.getName()).isNotNull();
        assertThat(errorDTO.getMessage()).isNotNull().isEqualTo("User not found exception");
        assertThat(errorDTO.getDetails()).isNotNull();
    }

    @SneakyThrows
    @Test
    @DisplayName("Delete one user by id when return user with successful")
    void deleteById() {
        var user = UserBuilder.builder(1L);
        var userDTO = service.create(mapper.map(user));

        mockMvc.perform(delete("/user/%s".formatted(userDTO.getId()))
                .headers(defaultHeaders())
                .contentType(APPLICATION_JSON)
            )
            .andExpect(status().isNoContent())
            .andReturn()
            .getResponse()
            .getContentAsString();

    }
}
