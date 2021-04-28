package com.example.TACS2021UTN.service.deck;

import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.DTO.DeckDTO;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;

import java.util.List;

public interface IDeckService {

    Deck getDeckByName(String name) throws  DeckNotFoundException;

    DeckDTO createDeck(DeckDTO deck);

    void deleteDeck(DeckDTO deck);

    void deleteDeckbyId(Long id);

    void updateDeck(Long deckId, Deck deck) throws  DeckNotFoundException;

    List<DeckDTO> getAllDecks();

    DeckDTO findById(Long id);

    List<CardDTO> getDeckCards(Long id);
}
