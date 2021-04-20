package com.example.TACS2021UTN.service.deck;

import com.example.TACS2021UTN.entities.Deck;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.repository.deck.IDeckRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class DeckService implements IDeckService {

    IDeckRepository deckRepository;

    public DeckService(IDeckRepository deckRepo){
        this.deckRepository = deckRepo;
    }

    public Deck getDeckBy(Long deckId) throws DeckNotFoundException {
        return deckRepository.findById(deckId)
                .orElseThrow(() -> new DeckNotFoundException(deckId.toString()));
    }

    public Deck getDeckByName(String deck) throws DeckNotFoundException {
        return deckRepository.findByName(deck)
                .orElseThrow(() -> new DeckNotFoundException(deck));
    }


    public Deck createDeck(@Valid Deck deck) {
        return deckRepository.save(deck);
    }

    public void deleteDeck(@Valid Deck deck) {
        deckRepository.delete(deck);
    }

    public void deleteDeckbyId(Long id) {
        deckRepository.deleteById(id);
    }

    @Override
    public Deck getDeckById(Long deckId) throws DeckNotFoundException {
        return deckRepository.findById(deckId)
                .orElseThrow(() -> new DeckNotFoundException(deckId.toString()));
    }

    @Override
    public Deck updateDeck(Long deckId, Deck deckDetails) throws DeckNotFoundException {

        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new DeckNotFoundException(deckId.toString()));

        deck.setCardList(deckDetails.getCardList());
        deck.setName(deckDetails.getName());
        Deck updatedItem = deckRepository.save(deck);

        return updatedItem;

    }

    @Override
    public List<Deck> getAllDecks() {
        return deckRepository.findAll();

    }


}
