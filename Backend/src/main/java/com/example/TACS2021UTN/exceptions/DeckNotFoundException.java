package com.example.TACS2021UTN.exceptions;

public class DeckNotFoundException extends Exception {

    private long deckId;


    public DeckNotFoundException(String param) {

            super(String.format("Deck is not found with  : '%s'", param));
        }




    }

