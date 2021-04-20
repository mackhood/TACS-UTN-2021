package com.example.TACS2021UTN.exceptions;

import com.example.TACS2021UTN.DTO.StatusCodeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
/*
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StatusCodeDTO> handleException(Exception errorException){
    }
*/
}
