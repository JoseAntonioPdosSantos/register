package com.lat.gsb.register.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class JwtUserDetailsBeanConfig {

    @Value("${security-api.jwt.subject}")
    private String jwtSubject;
    @Value("${security-api.security.username}")
    private String username;
    @Value("${security-api.security.password}")
    private String password;

    @Bean("userDetails")
    public UserDetails getUserDetails() {
        return User.withUsername(username).password(password).roles(jwtSubject).build();
    }
}
