package com.lat.gsb.register.config;


import com.lat.gsb.register.dto.auth.AuthenticationDTO;
import com.lat.gsb.register.dto.auth.LoginDTO;
import com.lat.gsb.register.exception.jwt.InvalidSubjectTokenException;
import com.lat.gsb.register.exception.jwt.JwtBadCredentialsException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${security-api.jwt.secret}")
    private String jwtSecret;

    @Value("${security-api.jwt.expirationInMs}")
    private int jwtExpirationInMs;

    @Value("${security-api.jwt.subject}")
    private String jwtSubject;

    @Value("${security-api.jwt.prefix_token}")
    private String prefixToken;

    private final UserDetails defaultUser;

    public AuthenticationDTO authenticate(LoginDTO loginDTO) {
        boolean isInvalidCredential = !loginDTO.equals(defaultUser);
        if (isInvalidCredential) {
            throw new JwtBadCredentialsException();
        }
        return new AuthenticationDTO(prefixToken, generateToken());
    }

    public void validJWT(String token) {
        validateToken(token);
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        boolean notEqualsSubject = !claims.getSubject().equals(jwtSubject);
        if (notEqualsSubject) {
            throw new InvalidSubjectTokenException();
        }
    }

    public String generateToken() {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder().setSubject(jwtSubject).setIssuedAt(now).setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    private void validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty");
        }
    }
}
