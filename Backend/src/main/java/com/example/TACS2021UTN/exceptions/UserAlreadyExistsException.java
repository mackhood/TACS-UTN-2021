package com.example.TACS2021UTN.exceptions;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String username){
        super(String.format("Username %s is invalid, try another one",username));
    }
}
