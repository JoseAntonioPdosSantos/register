package com.lat.gsb.register;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lat.gsb.register.dto.auth.AuthenticationDTO;
import com.lat.gsb.register.dto.auth.LoginDTO;
import de.cronn.testutils.h2.H2Util;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RegisterApplication.class)
@Import({H2Util.class})
@AutoConfigureMockMvc
public abstract class IntegrationTestConfig {

    private static String accessToken;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected Environment environment;

    @Mock
    protected ApplicationContext context;

    @Value("${security-api.security.path}")
    private String authPath;

    @Value("${security-api.security.username}")
    private String username;

    @Value("${security-api.security.password}")
    private String password;

    @BeforeEach
    public void setup(@Autowired H2Util h2Util) {
        createAccessToken();
    }

    private void createAccessToken() {
        var loginDTO = this.getAuthenticationDTO();
        accessToken = loginDTO.getToken();
    }

    protected HttpHeaders defaultHeaders() {
        return authHeader();
    }

    protected HttpHeaders authHeader() {
        var headers = new HttpHeaders();
        headers.add("Authorization", "Bearer %s".formatted(accessToken));
        return headers;
    }

    @SneakyThrows
    private AuthenticationDTO getAuthenticationDTO() {
        LoginDTO loginDto = LoginDTO.builder()
            .username(username)
            .password(password)
            .build();

        var response = mockMvc.perform(post(authPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        return objectMapper.readValue(response, AuthenticationDTO.class);
    }

}
