package com.lat.gsb.register.dto.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationDTO {

    private String prefix;
    private String token;

    public String toString() {
        return "%s %s".formatted(prefix, token);
    }
}
