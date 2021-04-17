package com.example.TACS2021UTN.service;

import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;

public interface IDeckService {

    Deck getDeckByName(String name) throws  DeckNotFoundException;

    Deck createDeck(Deck deck);

    void deleteDeck(Deck deck);

    Deck  getDeckById(Long deckId) throws  DeckNotFoundException;

    Deck updateDeck(Long deckId, Deck deck) throws  DeckNotFoundException;
}
