package com.lat.gsb.register.controller.interf;

import com.lat.gsb.register.dto.user.UserDTO;
import com.lat.gsb.register.dto.user.UserRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Api("User Controller")
public interface UserController {

    @ApiOperation("Create user")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Created", response = UserDTO.class),
        @ApiResponse(code = 404, message = "Not Found", response = HttpClientErrorException.BadRequest.class),
        @ApiResponse(code = 409, message = "Conflict", response = HttpClientErrorException.BadRequest.class),
        @ApiResponse(code = 500, message = "Internal server error.",
            response = HttpClientErrorException.BadRequest.class)
    })
    ResponseEntity<UserDTO> save(UserRequestDTO userDTO);

    @ApiOperation("Update user")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK", response = UserDTO.class),
        @ApiResponse(code = 404, message = "Not Found", response = HttpClientErrorException.BadRequest.class),
        @ApiResponse(code = 409, message = "Conflict", response = HttpClientErrorException.BadRequest.class),
        @ApiResponse(code = 500, message = "Internal server error.",
            response = HttpClientErrorException.BadRequest.class)
    })
    ResponseEntity<UserDTO> update(Long id, UserRequestDTO userDTO);

    @ApiOperation("Find all users")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK", response = UserDTO.class),
        @ApiResponse(code = 404, message = "Not Found", response = HttpClientErrorException.BadRequest.class),
        @ApiResponse(code = 409, message = "Conflict", response = HttpClientErrorException.BadRequest.class),
        @ApiResponse(code = 500, message = "Internal server error.",
            response = HttpClientErrorException.BadRequest.class)
    })
    ResponseEntity<List<UserDTO>> findAll();

    @ApiOperation("Find one user by id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK", response = UserDTO.class),
        @ApiResponse(code = 404, message = "Not Found", response = HttpClientErrorException.BadRequest.class),
        @ApiResponse(code = 409, message = "Conflict", response = HttpClientErrorException.BadRequest.class),
        @ApiResponse(code = 500, message = "Internal server error.",
            response = HttpClientErrorException.BadRequest.class)
    })
    ResponseEntity<UserDTO> findOneById(Long id);

    @ApiOperation("Delete one user by id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "NoContent"),
        @ApiResponse(code = 404, message = "Not Found", response = HttpClientErrorException.BadRequest.class),
        @ApiResponse(code = 409, message = "Conflict", response = HttpClientErrorException.BadRequest.class),
        @ApiResponse(code = 500, message = "Internal server error.",
            response = HttpClientErrorException.BadRequest.class)
    })
    ResponseEntity<UserDTO> delete(Long id);
}
