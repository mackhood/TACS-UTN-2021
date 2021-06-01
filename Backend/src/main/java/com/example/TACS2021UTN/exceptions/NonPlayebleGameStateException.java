package com.example.TACS2021UTN.exceptions;

public class NonPlayebleGameStateException extends Exception{
    public NonPlayebleGameStateException(String param) {
        super(String.format("The game is in a non playable state: '%s'", param));
    }
}
