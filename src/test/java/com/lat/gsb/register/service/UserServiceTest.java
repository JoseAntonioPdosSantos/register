package com.lat.gsb.register.service;

import com.lat.gsb.register.UnitTestConfig;
import com.lat.gsb.register.builder.UserBuilder;
import com.lat.gsb.register.builder.UserRequestDTOBuilder;
import com.lat.gsb.register.mapper.UserMapper;
import com.lat.gsb.register.mapper.UserRequestMapper;
import com.lat.gsb.register.model.User;
import com.lat.gsb.register.repository.UserRepository;
import com.lat.gsb.register.util.CriptUtil;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UserServiceTest extends UnitTestConfig {

    @InjectMocks
    private UserService service;
    @Mock
    private UserRepository repository;
    @Spy
    private UserMapper mapper = Mappers.getMapper(UserMapper.class);
    @Spy
    private UserRequestMapper userRequestMapper = Mappers.getMapper(UserRequestMapper.class);

    @Test
    void create() {
        var user = UserBuilder.builder(1L);
        user.setPassword(CriptUtil.encript(user.getPassword()));
        var userRequestDTO = UserRequestDTOBuilder.builder();

        when(repository.save(any(User.class))).thenReturn(user);

        var userDTO = service.create(userRequestDTO);

        assertThat(userDTO).isNotNull();
        assertThat(userDTO.getId()).isNotNull().isEqualTo(1L);
        assertThat(userDTO.getName()).isNotNull().isEqualTo("Some name");
        assertThat(userDTO.getEmail()).isNotNull().isEqualTo("some_email@valid.com");
        assertThat(userDTO.getCellphone()).isNotNull().isEqualTo("+5599999999999");
        assertThat(userDTO.getUsername()).isNotNull().isEqualTo("Some username");
    }

    @Test
    void update() {
        var user = UserBuilder.builder(1L);
        user.setEmail("other_email@email.valid");
        user.setPassword(CriptUtil.encript(user.getPassword()));

        var userRequestDTO = UserRequestDTOBuilder.builder(1L);
        userRequestDTO.setEmail("other_email@email.valid");

        when(repository.save(any(User.class))).thenReturn(user);
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));

        var userCreated = service.update(1L, userRequestDTO);

        assertThat(userCreated).isNotNull();
        assertThat(userCreated.getId()).isNotNull().isEqualTo(1L);
        assertThat(userCreated.getName()).isNotNull().isEqualTo("Some name");
        assertThat(userCreated.getEmail()).isNotNull().isEqualTo("other_email@email.valid");
        assertThat(userCreated.getCellphone()).isNotNull().isEqualTo("+5599999999999");
        assertThat(userCreated.getUsername()).isNotNull().isEqualTo("Some username");
    }

    @Test
    void findAll() {
        var user1 = UserBuilder.builder(1L);
        var user2 = UserBuilder.builder(2L);
        var user3 = UserBuilder.builder(3L);

        when(repository.findAll()).thenReturn(List.of(user1, user2, user3));

        var allUsers = service.findAll();

        assertThat(allUsers).isNotNull().isNotEmpty().hasSize(3);
    }

    @Test
    void findById() {
        var user = UserBuilder.builder(1L);
        user.setPassword(CriptUtil.encript(user.getPassword()));

        when(repository.findById(anyLong())).thenReturn(Optional.of(user));

        var userFound = service.findById(1L);

        assertThat(userFound).isNotNull();
        assertThat(userFound.getId()).isNotNull().isEqualTo(1L);
        assertThat(userFound.getName()).isNotNull().isEqualTo("Some name");
        assertThat(userFound.getEmail()).isNotNull().isEqualTo("some_email@valid.com");
        assertThat(userFound.getCellphone()).isNotNull().isEqualTo("+5599999999999");
        assertThat(userFound.getUsername()).isNotNull().isEqualTo("Some username");
    }

    @Test
    void delete() {
        var user = UserBuilder.builder(1L);

        when(repository.findById(anyLong())).thenReturn(Optional.of(user));
        doNothing().when(repository).deleteById(anyLong());

        service.delete(1L);

        verify(repository, times(1)).deleteById(anyLong());
    }
}
