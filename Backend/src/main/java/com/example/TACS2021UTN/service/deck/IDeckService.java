package com.example.TACS2021UTN.service.deck;

import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;

import java.util.List;

public interface IDeckService {

    Deck getDeckByName(String name) throws  DeckNotFoundException;

    Deck createDeck(Deck deck);

    void deleteDeck(Deck deck);

    void deleteDeckbyId(Long id);

    Deck getDeckById(Long deckId) throws DeckNotFoundException;

    Deck updateDeck(Long deckId, Deck deck) throws  DeckNotFoundException;

    List<Deck> getAllDecks();
}
