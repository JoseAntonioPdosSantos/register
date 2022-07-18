package com.lat.gsb.register.handle;


import com.lat.gsb.register.dto.ErrorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

@ControllerAdvice
@RequiredArgsConstructor
public class RegisterExceptionHandler {


    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ErrorDTO> handleConflict(Exception ex, WebRequest request) {
        var error = getErrorDTO(ex.getLocalizedMessage(), ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<ErrorDTO> handleRuntimeException(RuntimeException ex, WebRequest request) {
        var error = getErrorDTO(ex.getLocalizedMessage(), ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private ErrorDTO getErrorDTO(String ex, String ex1) {
        var details = new HashMap<String, String>();
        details.put("Exception", ex);

        return ErrorDTO.builder()
            .details(details)
            .name(HttpStatus.BAD_REQUEST.toString())
            .message(ex1)
            .build();
    }

}
