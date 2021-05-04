package com.example.TACS2021UTN.exceptions;

public class CardNotFoundException extends Exception {

    private long deckId;


    public CardNotFoundException(String param) {

            super(String.format("Card not found with id : '%s'", param));
        }




    }

