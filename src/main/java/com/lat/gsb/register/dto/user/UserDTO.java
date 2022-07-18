package com.lat.gsb.register.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDTO {

    @ApiModelProperty(
        value = "User ID.",
        dataType = "long",
        example = "1",
        hidden = true
    )
    private Long id;

    @NotNull
    @ApiModelProperty(
        value = "User name.",
        dataType = "string",
        example = "David Guimaraes",
        required = true
    )
    private String name;
    @NotBlank
    @Email
    @ApiModelProperty(
        value = "User e-mail.",
        dataType = "string",
        example = "your_email@email.com",
        required = true
    )
    private String email;
    @NotNull
    @ApiModelProperty(
        value = "User cellphone.",
        dataType = "string",
        example = "+55 4 8546-4652",
        required = true
    )
    private String cellphone;
    @NotNull
    @ApiModelProperty(
        value = "User username.",
        dataType = "string",
        example = "your username",
        required = true
    )
    private String username;

}
