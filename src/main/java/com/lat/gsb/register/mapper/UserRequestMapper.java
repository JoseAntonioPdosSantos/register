package com.lat.gsb.register.mapper;

import com.lat.gsb.register.dto.user.UserDTO;
import com.lat.gsb.register.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserDTO value);

    UserDTO map(User value);

    List<UserDTO> map(List<User> values);
}
