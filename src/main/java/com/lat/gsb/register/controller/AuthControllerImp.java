package com.lat.gsb.register.controller;

import com.lat.gsb.register.config.JwtTokenProvider;
import com.lat.gsb.register.controller.interf.AuthController;
import com.lat.gsb.register.dto.auth.AuthenticationDTO;
import com.lat.gsb.register.dto.auth.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImp implements AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("${security-api.security.path}")
    @Override
    public AuthenticationDTO auth(@RequestBody LoginDTO loginDTO) {
        return jwtTokenProvider.authenticate(loginDTO);
    }
}
