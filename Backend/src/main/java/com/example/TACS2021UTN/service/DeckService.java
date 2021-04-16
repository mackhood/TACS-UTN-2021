package com.example.TACS2021UTN.service;

import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.repository.DeckRepository;
import org.springframework.stereotype.Service;

@Service
public class DeckService implements IDeckService {

    DeckRepository deckRepository;

    public Deck getDeckBy(Long deckId) throws DeckNotFoundException {
        return deckRepository.findById(deckId)
                .orElseThrow(() -> new DeckNotFoundException(deckId.toString()));
    }

    public Deck getDeckByName(String deck) throws DeckNotFoundException {
        return deckRepository.findByName(deck)
                .orElseThrow(() -> new DeckNotFoundException(deck));
    }
}
