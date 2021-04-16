package com.example.TACS2021UTN.exceptions;

public class DeckNotFoundException extends Exception {

    private long deckId;


    public DeckNotFoundException(String deckId) {

            super(String.format("Deck is not found with id : '%s'", deckId));
        }




    }

