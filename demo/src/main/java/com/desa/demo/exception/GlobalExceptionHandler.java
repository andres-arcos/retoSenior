package com.desa.demo.exception;

import com.desa.demo.service.dto.ClienteDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteException.class)
    public ResponseEntity<ClienteDto> handleClienteDuplicado(ClienteException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ClienteDto(
                        null, null, null, null,null,
                        null, null, null, null,null,
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ClienteDto> handleGeneral(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ClienteDto(
                        null, null, null, null,null,
                        null, null, null, null,null,
                        "Error interno del servidor"
                ));
    }
}

