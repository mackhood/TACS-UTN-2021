package com.example.TACS2021UTN.service.deck;

import com.example.TACS2021UTN.DTO.CardDTO;
import com.example.TACS2021UTN.DTO.DeckDTO;
import com.example.TACS2021UTN.exceptions.NotFoundException;
import com.example.TACS2021UTN.models.Card;
import com.example.TACS2021UTN.models.Deck;
import com.example.TACS2021UTN.exceptions.DeckNotFoundException;
import com.example.TACS2021UTN.repositories.card.ICardRepository;
import com.example.TACS2021UTN.repositories.deck.IDeckRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeckService implements IDeckService {

    private IDeckRepository deckRepository;
    private ModelMapper  modelMapper;

    public DeckService(IDeckRepository deckRepo, ModelMapper modelMapper){
        this.deckRepository = deckRepo;
        this.modelMapper = modelMapper;;
    }

    public Deck getDeckBy(Long deckId) throws DeckNotFoundException {
        return deckRepository.findById(deckId)
                .orElseThrow(() -> new DeckNotFoundException(deckId.toString()));
    }

    public Deck getDeckByName(String deck) throws DeckNotFoundException {
        return deckRepository.findByName(deck)
                .orElseThrow(() -> new DeckNotFoundException(deck));
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
                .orElseThrow(() -> new NotFoundException(
                        "Deck not found with id: " + deckId.toString())
                );

        deck.setCardList(deckDetails.getCardList());
        deck.setName(deckDetails.getName());
        Deck updatedItem = deckRepository.save(deck);

        return updatedItem;

    }

    @Override
    public List<DeckDTO> getAllDecks() {
        List<Deck> decks = deckRepository.findAll();
        List<DeckDTO> deckDTOS =
                decks.stream().map(d -> modelMapper.map(d, DeckDTO.class)).collect(Collectors.toList());
        return deckDTOS;
    }

    @Override
    public DeckDTO findById(long id)
    {
        Deck deck = deckRepository.findById(id).orElseThrow(() ->
             new NotFoundException("Deck not found with id: " + id)
        );
        DeckDTO cardDTO = modelMapper.map(deck, DeckDTO.class);
        return cardDTO;
    }

    @Override
    public DeckDTO createDeck(DeckDTO deckRequest)
    {
        Deck deck = modelMapper.map(deckRequest, Deck.class);
        //validate cards with all required atributtes

        deckRepository.save(deck);

        return deckRequest;
    }
}
