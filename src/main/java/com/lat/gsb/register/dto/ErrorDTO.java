package com.lat.gsb.register.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO {

    @ApiModelProperty(value = "Error title", dataType = "String", required = true)
    private String name;
    @ApiModelProperty(value = "Error message", dataType = "String", required = true)
    private String message;

    @ApiModelProperty(value = "Error message", dataType = "String", required = true)
    private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

    @ApiModelProperty(value = "Additional error messages", dataType = "Map<Title,Message>")
    private Object details;
}
