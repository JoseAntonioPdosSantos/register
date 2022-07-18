package com.lat.gsb.register.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserRequestDTO extends UserDTO {

    @NotNull
    @ApiModelProperty(
        value = "User password.",
        dataType = "string",
        example = "your password",
        required = true
    )
    private String password;
}
