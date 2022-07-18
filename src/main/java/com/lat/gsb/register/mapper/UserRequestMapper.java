package com.lat.gsb.register.mapper;

import com.lat.gsb.register.dto.user.UserRequestDTO;
import com.lat.gsb.register.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {

    User map(UserRequestDTO value);

    UserRequestDTO map(User value);

    List<UserRequestDTO> map(List<User> values);
}
