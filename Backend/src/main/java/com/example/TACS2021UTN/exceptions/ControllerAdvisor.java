package com.example.TACS2021UTN.exceptions;

import com.example.TACS2021UTN.DTO.StatusCodeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StatusCodeDTO> handleException(NotFoundException errorException)
    {
        StatusCodeDTO statusCode = new StatusCodeDTO();
        statusCode.setCode(HttpStatus.NOT_FOUND.value());
        statusCode.setMessage(errorException.getMessage());
        return new ResponseEntity<>(statusCode,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<StatusCodeDTO> handleException(CardNotFoundException errorException)
    {
        StatusCodeDTO statusCode = new StatusCodeDTO();
        statusCode.setCode(HttpStatus.NOT_FOUND.value());
        statusCode.setMessage(errorException.getMessage());
        return new ResponseEntity<>(statusCode,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserWithoutTurnException.class)
    public ResponseEntity<StatusCodeDTO> handleException(UserWithoutTurnException errorException)
    {
        StatusCodeDTO statusCode = new StatusCodeDTO();
        statusCode.setCode(HttpStatus.FORBIDDEN.value());
        statusCode.setMessage(errorException.getMessage());
        return new ResponseEntity<>(statusCode,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NonPlayebleGameStateException.class)
    public ResponseEntity<StatusCodeDTO> handleException(NonPlayebleGameStateException errorException)
    {
        StatusCodeDTO statusCode = new StatusCodeDTO();
        statusCode.setCode(HttpStatus.FORBIDDEN.value());
        statusCode.setMessage(errorException.getMessage());
        return new ResponseEntity<>(statusCode,HttpStatus.FORBIDDEN);
    }
}
