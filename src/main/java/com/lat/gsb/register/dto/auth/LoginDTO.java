package com.lat.gsb.register.dto.auth;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private String username;
    private String password;

    public boolean equals(UserDetails userDetails) {
        return userDetails.getUsername().equals(username) && userDetails.getPassword().equals(password);
    }

}
