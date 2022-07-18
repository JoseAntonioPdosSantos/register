package com.lat.gsb.register.controller.interf;

import com.lat.gsb.register.dto.auth.AuthenticationDTO;
import com.lat.gsb.register.dto.auth.LoginDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("Auth Controller")
public interface AuthController {

    @ApiOperation("A request to get authentication token.")
    AuthenticationDTO auth(LoginDTO loginDTO);
}
