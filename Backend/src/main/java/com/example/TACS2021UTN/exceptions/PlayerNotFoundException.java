package com.example.TACS2021UTN.exceptions;

public class PlayerNotFoundException extends Exception {

    public PlayerNotFoundException(String param) {

        super(String.format("Deck is not found with  : '%s'", param));
    }





}
