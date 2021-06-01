package com.example.TACS2021UTN.exceptions;

public class UserWithoutTurnException extends Exception{
    public UserWithoutTurnException(String param) {
        super(String.format("The turn does not belong to the user: '%s'", param));
    }
}
