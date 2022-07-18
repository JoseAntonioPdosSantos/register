package com.lat.gsb.register.controller;

import com.lat.gsb.register.IntegrationTestConfig;
import com.lat.gsb.register.builder.UserBuilder;
import com.lat.gsb.register.builder.UserRequestDTOBuilder;
import com.lat.gsb.register.dto.ErrorDTO;
import com.lat.gsb.register.dto.user.UserDTO;
import com.lat.gsb.register.mapper.UserMapper;
import com.lat.gsb.register.mapper.UserRequestMapper;
import com.lat.gsb.register.service.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("UserControllerImp Tests")
class UserControllerImpTest extends IntegrationTestConfig {

    @Autowired
    private UserService service;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private UserRequestMapper userRequestMapper;

    @SneakyThrows
    @Test
    @DisplayName("Create user when return user with successful")
    void save() {
        var userRequestDTO = UserRequestDTOBuilder.builder(1L);

        var response = mockMvc.perform(post("/user")
                .headers(defaultHeaders())
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDTO))
            )
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString();

        var userDTO = objectMapper.readValue(response, UserDTO.class);
        assertNotNull(userDTO);
        assertThat(userDTO.getId()).isNotNull();
        assertThat(userDTO.getName()).isNotNull().isEqualTo("Some name");
        assertThat(userDTO.getEmail()).isNotNull().isEqualTo("some_email@valid.com");
        assertThat(userDTO.getCellphone()).isNotNull().isEqualTo("+5599999999999");
        assertThat(userDTO.getUsername()).isNotNull().isEqualTo("some username");
    }

    @Test
    @DisplayName("Create user when return bad request with fail")
    void save_when_fail() throws Exception {
        var userRequestDTO = UserRequestDTOBuilder.builder(1L);
        userRequestDTO.setName("1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-"
            + "418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1"
            + "-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f"
            + "1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-"
            + "418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1"
            + "-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f"
        );

        var response = mockMvc.perform(post("/user")
                .headers(defaultHeaders())
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDTO))
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

        var userDTO = service.create(userRequestMapper.map(user));
        var userRequestDTO = UserRequestDTOBuilder.builder(userDTO.getId());
        userRequestDTO.setEmail("change_email@email.valid");

        var response = mockMvc.perform(put("/user/%s".formatted(userDTO.getId()))
                .headers(defaultHeaders())
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDTO))
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
        assertThat(responseDTO.getUsername()).isNotNull().isEqualTo("some username");
    }

    @Test
    @DisplayName("Update user when return bad request with fail")
    void update_when_fail() throws Exception {
        var requestDTO = UserRequestDTOBuilder.builder(1L);

        var userDTO = service.create(requestDTO);

        var userRequestDTO = UserRequestDTOBuilder.builder(userDTO.getId());
        userRequestDTO.setEmail("change_email@email.valid");
        userRequestDTO.setName("1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-"
            + "418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1"
            + "-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f"
            + "1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-"
            + "418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f1c9495a1"
            + "-4380-418b-a1be-d16b8c5f861f1c9495a1-4380-418b-a1be-d16b8c5f861f"
        );

        var response = mockMvc.perform(put("/user/%s".formatted(userRequestDTO.getId()))
                .headers(defaultHeaders())
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDTO))
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
        var user1 = UserRequestDTOBuilder.builder(null);
        service.create(user1);
        var user2 = UserRequestDTOBuilder.builder(null);
        service.create(user2);
        var user3 = UserRequestDTOBuilder.builder(null);
        service.create(user3);

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
        var user = UserRequestDTOBuilder.builder(1L);
        var userDTO = service.create(user);

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
        assertThat(responseDTO.getUsername()).isNotNull().isEqualTo("some username");
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
        var user = UserRequestDTOBuilder.builder(1L);
        var userDTO = service.create(user);

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
