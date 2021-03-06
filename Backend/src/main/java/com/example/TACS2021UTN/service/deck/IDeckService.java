package com.example.TACS2021UTN.service.deck;

import com.example.TACS2021UTN.DTO.CardApiDTO;
import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.DTO.DeckDTO;
import com.example.TACS2021UTN.DTO.request.DeckRequestDTO;
import com.example.TACS2021UTN.exceptions.CardNotFoundException;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDeckService {

    Deck getDeckByName(String name) throws  DeckNotFoundException;

    DeckDTO save(DeckRequestDTO deck) throws CardNotFoundException;

    void deleteDeckbyId(Long id);

    void updateDeck(Long deckId, DeckRequestDTO deck);

    List<DeckDTO> getAllDecks(Pageable paging);

    DeckDTO findById(Long id);

    List<CardDTO> getDeckCards(Long id);
}
