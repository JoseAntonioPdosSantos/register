package com.lat.gsb.register.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@EnableSwagger2
@SpringBootConfiguration
public class SwaggerConfig {

    @Value("${swagger.info.version}")
    private String version;
    @Value("${swagger.info.title}")
    private String title;
    @Value("${swagger.info.description}")
    private String description;
    @Value("${swagger.info.termsOfServiceUrl}")
    private String termsOfServiceUrl;
    @Value("${swagger.info.contact.name}")
    private String contactName;
    @Value("${swagger.info.contact.url}")
    private String contactUrl;
    @Value("${swagger.info.contact.email}")
    private String contactEmail;

    @Value("${swagger.path_api_scan}")
    private String pathApiScan;

    @Value("${security-api.jwt.authorization_header}")
    private String headerKey;

    @Bean
    public Docket api() throws Exception {
        return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .select().apis(RequestHandlerSelectors.basePackage(pathApiScan))
            .paths(PathSelectors.any())
            .build()
            .securityContexts(securityContext())
            .securitySchemes(apiKey())
            .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        Contact contact = new Contact(contactName, contactUrl, contactEmail);
        return new ApiInfo(title, description, version, termsOfServiceUrl, contact, "license", "licenseUrl",
            Collections.emptyList());
    }

    private List<SecurityContext> securityContext() {
        SecurityContext context = SecurityContext.builder().securityReferences(defaultAuth()).build();
        return List.of(context);
    }

    List<SecurityReference> defaultAuth() {
        var authorizationScope
            = new AuthorizationScope("global", "accessEverything");
        var authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        return List.of(
            new SecurityReference("JWT", authorizationScopes));
    }

    private List<SecurityScheme> apiKey() {
        return List.of(new ApiKey("JWT", headerKey, "header"));
    }
}
